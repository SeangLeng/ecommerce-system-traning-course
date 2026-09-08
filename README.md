# ecommerce-system

Maven multi-module repository for an ecommerce platform. The root aggregator (`ecommerce-system`, group `mptc.seangleng`) is a Spring Boot 4.1.1 / Java 25 project with `packaging: pom`. Source directories exist, but application code has not been added yet.

```
ecommerce-system
├── common              shared libraries used by every service
└── order-service       first bounded-context microservice (template for later services)
```

Build everything from the repo root:

```bash
./mvnw clean install
```

## Architecture

Each service follows hexagonal (ports and adapters) layout with DDD-style layers. Inner modules never depend on adapters. Domain core has no Spring, JPA, or HTTP. Application service depends only on domain core. Adapters implement ports defined in application service. `order-service-main` is the composition root (the Spring Boot application).

```
                     order-service-main
                            |
          +-----------------+-----------------+
          |                 |                 |
   restapi adapter   persistence adapter   messaging adapter
          |                 |                 |
          +--------+--------+--------+--------+
                   |
         order-application-service
                   |
           order-domain-core
                   |
             common-domain
```

## Modules

### `common`

Shared libraries. Every future service should reuse these instead of duplicating primitives or bootstrap.

| Module | Artifact | Role |
|---|---|---|
| `common/common-domain` | `common-domain` | Shared domain primitives (base types used by every service) |
| `common/common-restapi` | `common-restapi` | Shared REST concerns (error handling, common API types) |
| `common/common-starter` | `common-starter` | Shared Spring Boot bootstrap / auto-configuration |

Business rules for a bounded context belong in that service's `*-domain-core` module (for example `order-domain-core`), not in `common-domain`.

### `order-service`

First microservice. Copy this tree when adding another service.

| Module | Artifact | Role |
|---|---|---|
| `order-service` | `order-service` | Aggregator POM for the service |
| `order-service-domain` | `order-service-domain` | Aggregator for domain + application |
| `order-service-domain/order-domain-core` | `order-domain-core` | Critical order logic: aggregates, value objects, domain services |
| `order-service-domain/order-application-service` | `order-application-service` | Use cases and ports (interfaces adapters implement) |
| `order-service-persistance` | `order-service-persistance` | Persistence adapter (JPA / database) |
| `order-service-restapi` | `order-service-restapi` | REST adapter (controllers, request/response mapping) |
| `order-service-messaging` | `order-service-messaging` | Messaging adapter (events, brokers) |
| `order-service-main` | `order-service-main` | Runnable Spring Boot module; wires adapters to application service |

The persistence module is named `persistance` in the repository; keep that spelling when referring to the Maven artifact.

## Version inheritance

Internal module versions are pinned once in the root POM `dependencyManagement` block, then child modules declare the dependency **without** `<version>`. Maven resolves the version from the parent.

Root [`pom.xml`](pom.xml) currently manages:

- `common-domain` `0.0.1-SNAPSHOT`
- `order-domain-core` `0.0.1-SNAPSHOT`

Example from [`order-domain-core/pom.xml`](order-service/order-service-domain/order-domain-core/pom.xml):

```xml
<dependencies>
    <dependency>
        <groupId>mptc.seangleng</groupId>
        <artifactId>common-domain</artifactId>
        <!-- no version: inherited from parent dependencyManagement -->
    </dependency>
</dependencies>
```

`order-application-service` uses the same pattern for `order-domain-core`. When more internal modules are wired, add them to root `dependencyManagement` the same way.

## Adding another service

1. Copy the `order-service` module tree and rename artifacts (`payment-service`, `payment-domain-core`, and so on).
2. Register the new aggregator in the root `<modules>` list.
3. Add the new domain artifacts to root `dependencyManagement`.
4. Depend on `common-domain` / `common-restapi` / `common-starter` as needed, still without repeating versions.

## Stack

- Java 25
- Spring Boot 4.1.1
- Maven compiler plugin 3.15.0
- Group ID: `mptc.seangleng`
- Version: `0.0.1-SNAPSHOT`
