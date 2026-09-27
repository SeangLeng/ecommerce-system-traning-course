package mptc.seangleng.ecommerce.order.ecommerce.valueobject;

import java.util.UUID;

public record CreditEntryId(UUID value) {

    public UUID getValue() {
        return value;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID value;

        private Builder() {
        }

        public Builder value(UUID val) {
            value = val;
            return this;
        }

        public CreditEntryId build() {
            return new CreditEntryId(value);
        }
    }
}
