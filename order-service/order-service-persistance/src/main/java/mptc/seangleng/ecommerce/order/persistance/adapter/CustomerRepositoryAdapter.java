package mptc.seangleng.ecommerce.order.persistance.adapter;

import lombok.RequiredArgsConstructor;
import mptc.seangleng.ecommerce.order.entity.Customer;
import mptc.seangleng.ecommerce.order.persistance.mapper.CustomerPersistenceMapper;
import mptc.seangleng.ecommerce.order.persistance.mapper.OrderPersistenceMapper;
import mptc.seangleng.ecommerce.order.persistance.repository.CustomerJpaRepository;
import mptc.seangleng.ecommerce.order.port.output.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {
    private final CustomerPersistenceMapper customerPersistenceMapper;
    private final CustomerJpaRepository customerJpaRepository;

    @Override
    public Optional<Customer> findById(UUID customerId) {
        return customerJpaRepository.findById(customerId).map(customerPersistenceMapper::customerEntityToCustomer);
    }
}
