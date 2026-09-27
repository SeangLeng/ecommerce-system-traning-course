package mptc.seangleng.ecommerce.order.port.output;

import mptc.seangleng.ecommerce.order.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findById(UUID id);
}
