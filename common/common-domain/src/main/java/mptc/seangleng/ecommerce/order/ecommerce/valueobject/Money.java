package mptc.seangleng.ecommerce.order.ecommerce.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/*
 * Why we use record?
 * We use record, cuz we need inmutable class.
 */
public record Money(
        // Money amount
        BigDecimal amount) {
    public final static Money ZERO = new Money(BigDecimal.ZERO);

    // validate money > 0
    public Boolean isAmountGreaterThanZero() {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    // validate money input greater than original
    public Boolean isGreaterThan(Money money) {
        return this.amount.compareTo(money.amount) > 0;
    }

    public Boolean isGreaterThanEqual(Money money) {
        return this.amount.compareTo(money.amount) >= 0;
    }

    // adding money
    public Money add(Money money) {
        return new Money(setScale(this.amount.add(money.amount)));
    }

    // Set Scale of money
    private BigDecimal setScale(BigDecimal inputAmount) {
        return inputAmount.setScale(2, RoundingMode.HALF_EVEN);
    }

    // Subtract money
    public Money subtract(Money money) {
        return new Money(setScale(this.amount.subtract(money.amount)));
    }

    /*
     * Example: $20 x 10 = $200
     * Not $20 x $20
     * */
    public Money multiply(int multiplier) {
        return new Money(setScale(this.amount.multiply(BigDecimal.valueOf(multiplier))));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(setScale(amount), setScale(money.amount));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(setScale(amount));
    }
}
