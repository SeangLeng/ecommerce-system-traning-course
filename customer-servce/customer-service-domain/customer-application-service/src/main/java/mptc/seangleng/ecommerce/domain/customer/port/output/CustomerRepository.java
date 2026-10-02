package mptc.seangleng.ecommerce.domain.customer.port.output;

import mptc.seangleng.ecommerce.domain.entity.Customer;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.CustomerId;

import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(CustomerId customerId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
