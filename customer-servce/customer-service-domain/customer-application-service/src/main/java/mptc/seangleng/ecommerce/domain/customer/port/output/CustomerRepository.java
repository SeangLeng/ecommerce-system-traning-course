package mptc.seangleng.ecommerce.domain.customer.port.output;

import mptc.seangleng.ecommerce.common_core.PageResponse;
import mptc.seangleng.ecommerce.domain.customer.dto.CustomerResult;
import mptc.seangleng.ecommerce.domain.customer.dto.GetCustomerQuery;
import mptc.seangleng.ecommerce.domain.entity.Customer;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.CustomerId;

import java.util.Optional;

public interface CustomerRepository {
    PageResponse<CustomerResult> findAll(int page, int size);
    Customer save(Customer customer);
    Optional<Customer> findById(CustomerId customerId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
