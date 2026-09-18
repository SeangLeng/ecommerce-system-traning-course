package mptc.seangleng.ecommerce.order.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BusinessIdEntity implements Serializable {
    private UUID businessId;
    private UUID productId;
}
