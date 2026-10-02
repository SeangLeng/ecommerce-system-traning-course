package mptc.seangleng.ecommerce.domain.event;

import mptc.seangleng.ecommerce.domain.entity.Customer;
import mptc.seangleng.ecommerce.order.ecommerce.event.DomainEvent;

public class CustomerEvent implements DomainEvent<Customer> {
    private final Customer customer;

    public CustomerEvent(Customer customer){
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}