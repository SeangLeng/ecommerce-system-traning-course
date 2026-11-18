package mptc.seangleng.ecommerce.persistence.adapter;

import lombok.RequiredArgsConstructor;
import mptc.seangleng.ecommerce.common_core.PageResponse;
import mptc.seangleng.ecommerce.domain.customer.dto.CustomerResult;
import mptc.seangleng.ecommerce.domain.customer.port.output.CustomerRepository;
import mptc.seangleng.ecommerce.domain.entity.Customer;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.CustomerId;
import mptc.seangleng.ecommerce.persistence.entity.CustomerEntity;
import mptc.seangleng.ecommerce.persistence.mapper.CustomerPersistenceMapper;
import mptc.seangleng.ecommerce.persistence.repository.CustomerJpaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {
    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerPersistenceMapper customerPersistenceMapper;

    @Cacheable(key = "#page + '-' + #size", value = "customer")
    @Override
    public PageResponse<CustomerResult> findAll(int page, int size) {
        int requestedPage = Math.max(page, 1);
        Pageable pageable = PageRequest.of(requestedPage - 1, size);
        Page<CustomerEntity> customerEntitiesPages = customerJpaRepository.findAll(pageable);
        Page<CustomerResult> customerResultsPage = customerEntitiesPages.map(customerPersistenceMapper::toCustomerResult);
        return PageResponse.<CustomerResult>builder()
                .items(customerResultsPage.getContent())
                .page(requestedPage)
                .size(customerResultsPage.getSize())
                .totalPages(customerResultsPage.getTotalPages())
                .hasNext(customerResultsPage.hasNext())
                .build();
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity savedEntity = customerJpaRepository.save(
                customerPersistenceMapper.customerToCustomerEntity(customer));
        return customerPersistenceMapper.customerEntityToCustomer(savedEntity);
    }

    @Override
    public Optional<Customer> findById(CustomerId customerId) {
        return customerJpaRepository.findById(customerId.value())
                .map(customerPersistenceMapper::customerEntityToCustomer);
    }

    @Override
    public boolean existsByUsername(String username) {
        return customerJpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerJpaRepository.existsByEmail(email);
    }
}
