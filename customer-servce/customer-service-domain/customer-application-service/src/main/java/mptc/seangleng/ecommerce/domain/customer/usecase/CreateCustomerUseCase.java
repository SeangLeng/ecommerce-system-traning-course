package mptc.seangleng.ecommerce.domain.customer.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mptc.seangleng.ecommerce.domain.customer.dto.CreateCustomerCommand;
import mptc.seangleng.ecommerce.domain.customer.dto.CreateCustomerResult;
import mptc.seangleng.ecommerce.domain.customer.exception.CustomerAlreadyExistsException;
import mptc.seangleng.ecommerce.domain.customer.mapper.CustomerDataMapper;
import mptc.seangleng.ecommerce.domain.customer.port.output.CustomerRepository;
import mptc.seangleng.ecommerce.domain.entity.Customer;
import mptc.seangleng.ecommerce.domain.event.CustomerCreatedEvent;
import mptc.seangleng.ecommerce.domain.service.CustomerDomainService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateCustomerUseCase {
    private final CustomerDomainService customerDomainService; // business rules (from customer-domain-core)
    private final CustomerRepository customerRepository;
    private final CustomerDataMapper customerDataMapper;

    @Transactional // everything below runs in one DB transaction: if anything fails, nothing is saved
    public CreateCustomerResult execute(CreateCustomerCommand createCustomerCommand) {
        log.info("Execute CreateCustomerUseCase : {}", createCustomerCommand);

        if (customerRepository.existsByUsername(createCustomerCommand.username())) {
            throw new CustomerAlreadyExistsException("Username already exists");
        }
        if (customerRepository.existsByEmail(createCustomerCommand.email())) {
            throw new CustomerAlreadyExistsException("Email already exists");
        }

        Customer customer = customerDataMapper.createCustomerCommandToCustomer(createCustomerCommand);

        CustomerCreatedEvent customerCreatedEvent = customerDomainService.validateAndInitiateCustomer(customer);

        Customer savedCustomer = customerRepository.save(customer);

        log.info("Customer created with id: {} at {}",
                savedCustomer.getId().value(), customerCreatedEvent.getCreatedAt());

        return new CreateCustomerResult(savedCustomer.getId().value());
    }
}
