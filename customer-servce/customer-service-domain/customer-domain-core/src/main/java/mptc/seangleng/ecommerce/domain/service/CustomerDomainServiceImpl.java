package mptc.seangleng.ecommerce.domain.service;

import mptc.seangleng.ecommerce.domain.entity.Customer;
import mptc.seangleng.ecommerce.domain.event.CustomerCreatedEvent;
import mptc.seangleng.ecommerce.domain.event.CustomerDeactivatedEvent;
import mptc.seangleng.ecommerce.domain.event.CustomerUpdatedEvent;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.Email;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.PhoneNumber;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CustomerDomainServiceImpl implements CustomerDomainService {

    @Override
    public CustomerCreatedEvent validateAndInitiateCustomer(Customer customer) {
        customer.validateCustomer();
        customer.initiateCustomer();
        return new CustomerCreatedEvent(customer, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public CustomerUpdatedEvent updateCustomer(Customer customer, String familyName, String givenName,
                                               Email email, PhoneNumber phoneNumber) {
        customer.updateCustomer(familyName, givenName, email, phoneNumber);
        return new CustomerUpdatedEvent(customer, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public CustomerDeactivatedEvent deactivateCustomer(Customer customer) {
        customer.deactivateCustomer();
        return new CustomerDeactivatedEvent(customer.getId(), ZonedDateTime.now(ZoneId.of("UTC")));
    }
}
