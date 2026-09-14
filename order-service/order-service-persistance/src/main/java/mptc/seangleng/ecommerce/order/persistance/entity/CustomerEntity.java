package mptc.seangleng.ecommerce.order.persistance.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {
    @Id
    private UUID id;
    private String username;
    private String familyName;
    private String givenName;
}
