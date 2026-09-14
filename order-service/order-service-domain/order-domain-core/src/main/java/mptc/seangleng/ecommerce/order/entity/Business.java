package mptc.seangleng.ecommerce.order.entity;

import mptc.seangleng.ecommerce.order.ecommerce.entity.AggregateRoot;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.BusinessId;

import java.util.List;

public class Business extends AggregateRoot<BusinessId> {
    private final List<Product> products;
    private final Boolean active;

    public Business(List<Product> products, Boolean active) {
        this.products = products;
        this.active = active;
    }

    private Business(Builder builder) {
        super.setId(builder.id);
        products = builder.products;
        active = builder.active;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Boolean getActive() {
        return active;
    }

    public static final class Builder {
        private BusinessId id;
        private List<Product> products;
        private Boolean active;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(BusinessId val) {
            id = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Builder active(Boolean val) {
            active = val;
            return this;
        }

        public Business build() {
            return new Business(this);
        }
    }
}
