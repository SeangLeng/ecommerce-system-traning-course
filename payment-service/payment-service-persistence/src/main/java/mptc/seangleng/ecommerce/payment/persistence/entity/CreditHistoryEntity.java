package mptc.seangleng.ecommerce.payment.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "credit_history")
public class CreditHistoryEntity {
    @Id
    private UUID id;
    private UUID customerId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}
