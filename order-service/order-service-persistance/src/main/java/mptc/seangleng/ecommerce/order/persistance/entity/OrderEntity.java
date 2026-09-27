package mptc.seangleng.ecommerce.order.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.OrderStatus;
import org.springframework.data.domain.Persistable;

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
public class OrderEntity implements Persistable<UUID> {
    @Id
    private UUID id;

    private UUID customerId;
    private UUID businessId;

    private BigDecimal price;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "street_address_id")
    private OrderAddressEntity streetAddress;

    @Transient
    private boolean newEntity = true;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;

    private UUID trackId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String failureMessage;

    @Override
    public boolean isNew() {
        return newEntity;
    }

    @PostLoad
    @PostPersist
    void markNotNew() {
        this.newEntity = false;
    }
}
