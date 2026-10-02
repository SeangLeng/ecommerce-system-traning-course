package mptc.seangleng.ecommerce.domain.event;

import mptc.seangleng.ecommerce.domain.entity.Customer;

import java.time.ZonedDateTime;

public class CustomerUpdatedEvent extends CustomerEvent {
    private final ZonedDateTime updatedAt;

    public CustomerUpdatedEvent(Customer customer, ZonedDateTime updatedAt){
        super(customer);
        this.updatedAt = updatedAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }
}
