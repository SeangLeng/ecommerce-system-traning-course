package mptc.seangleng.ecommerce.order.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_address")
public class OrderAddressEntity {
    @Id
    private UUID id;
    private String street;
    private String postalCode;
    private String city;

    @OneToOne(mappedBy = "streetAddress")
    private OrderEntity orderEntity;
}
