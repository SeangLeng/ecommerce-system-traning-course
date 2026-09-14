package mptc.seangleng.ecommerce.order.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity // Create Table
@Table(name = "order_items")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String productName;
    private BigDecimal productPrice;

    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;

    @ManyToOne
    private OrderEntity order;
}
