package mptc.seangleng.ecommerce.order.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "businesses")
@IdClass(BusinessIdEntity.class)
public class BusinessEntity {
    @Id
    private UUID businessId;

    @Id
    private UUID productId;

    private Boolean businessActive;
    private String productName;
    private BigDecimal productPrice;
}
