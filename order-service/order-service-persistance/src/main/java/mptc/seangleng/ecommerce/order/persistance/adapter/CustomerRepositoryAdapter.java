package mptc.seangleng.ecommerce.order.persistance.adapter;

import lombok.RequiredArgsConstructor;
import mptc.seangleng.ecommerce.order.entity.Customer;
import mptc.seangleng.ecommerce.order.persistance.mapper.OrderPersistenceMapper;
import mptc.seangleng.ecommerce.order.persistance.repository.CustomerJPARepository;
import mptc.seangleng.ecommerce.order.port.output.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {
    private final CustomerJPARepository customerJPARepository;
    private final OrderPersistenceMapper orderPersistenceMapper;

    @Override
    public Optional<Customer> findById(UUID customerId) {
        return customerJPARepository.findById(customerId).map(orderPersistenceMapper::customerEntityToCustomer);
    }
}
