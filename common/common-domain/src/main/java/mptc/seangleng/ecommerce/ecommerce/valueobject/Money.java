package mptc.seangleng.ecommerce.ecommerce.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
    public Boolean isGreaterThan(Money amount) {
        return amount.amount().compareTo(BigDecimal.ZERO) > 0;
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
        return new Money(setScale(money.amount.subtract(money.amount)));
    }

    /*
     * Example: $20 x 10 = $200
     * Not $20 x $20
     * */
    public Money multiply(int multiplier) {
        return new Money(setScale(this.amount.multiply(BigDecimal.valueOf(multiplier))));
    }
}
