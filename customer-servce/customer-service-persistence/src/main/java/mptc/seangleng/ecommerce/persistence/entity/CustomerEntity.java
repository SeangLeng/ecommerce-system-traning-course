package mptc.seangleng.ecommerce.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.CustomerStatus;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.LoyaltyTier;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    private UUID id;

    @Column(unique = true)
    private String username;

    private String familyName;

    private String givenName;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private LoyaltyTier loyaltyTier;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;
}