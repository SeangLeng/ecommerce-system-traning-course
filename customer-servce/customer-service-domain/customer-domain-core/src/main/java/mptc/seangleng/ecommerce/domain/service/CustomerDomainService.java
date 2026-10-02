package mptc.seangleng.ecommerce.domain.service;

import mptc.seangleng.ecommerce.domain.entity.Customer;
import mptc.seangleng.ecommerce.domain.event.CustomerCreatedEvent;
import mptc.seangleng.ecommerce.domain.event.CustomerDeactivatedEvent;
import mptc.seangleng.ecommerce.domain.event.CustomerUpdatedEvent;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.Email;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.PhoneNumber;

public interface CustomerDomainService {
    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);

    CustomerUpdatedEvent updateCustomer(Customer customer, String familyName, String givenName,
                                        Email email, PhoneNumber phoneNumber);

    CustomerDeactivatedEvent deactivateCustomer(Customer customer);
}
