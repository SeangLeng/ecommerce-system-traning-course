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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String Street;
    private String PastaCode;
    private String City;
    private String StreetAddress;

    @OneToOne(mappedBy = "streetAddress")
    private OrderEntity orderEntity;
}
