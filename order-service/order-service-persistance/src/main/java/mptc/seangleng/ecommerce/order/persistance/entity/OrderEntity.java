package mptc.seangleng.ecommerce.order.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mptc.seangleng.ecommerce.ecommerce.valueobject.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * JPA Entity must be POJO (Plan Old JAVA Object) class, we need getter_setter_NoArgsConstructor
 */
@Getter
@Setter
@NoArgsConstructor
@Entity // Create Table
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID customerId;
    private UUID businessId;

    private BigDecimal price;

    @OneToOne
    private OrderAddressEntity streetAddress;

    @OneToMany
    private List<OrderItemEntity> items;

    private UUID trackId;
    private OrderStatus orderStatus;
    private String[] failureMessage;
}
