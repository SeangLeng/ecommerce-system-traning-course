package mptc.seangleng.ecommerce.order.entity;

import mptc.seangleng.ecommerce.order.ecommerce.entity.BaseEntity;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.Money;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
    private final String name;
    private final Money price;

    private Product(Builder builder) {
        super.setId(builder.id);
        name = builder.name;
        price = builder.price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ProductId id;
        private String name;
        private Money price;

        private Builder() {
        }

        public Builder id(ProductId val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
