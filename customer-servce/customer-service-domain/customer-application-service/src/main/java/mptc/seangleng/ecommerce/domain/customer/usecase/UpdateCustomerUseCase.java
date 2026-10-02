package mptc.seangleng.ecommerce.domain.customer.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mptc.seangleng.ecommerce.domain.customer.dto.UpdateCustomerCommand;
import mptc.seangleng.ecommerce.domain.customer.dto.UpdateCustomerResult;
import mptc.seangleng.ecommerce.domain.customer.exception.CustomerAlreadyExistsException;
import mptc.seangleng.ecommerce.domain.customer.exception.CustomerNotFoundException;
import mptc.seangleng.ecommerce.domain.customer.mapper.CustomerDataMapper;
import mptc.seangleng.ecommerce.domain.customer.port.output.CustomerRepository;
import mptc.seangleng.ecommerce.domain.entity.Customer;
import mptc.seangleng.ecommerce.domain.event.CustomerUpdatedEvent;
import mptc.seangleng.ecommerce.domain.service.CustomerDomainService;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.CustomerId;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.Email;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateCustomerUseCase {
    private final CustomerDomainService customerDomainService;
    private final CustomerRepository customerRepository;
    private final CustomerDataMapper customerDataMapper;

    @Transactional
    public UpdateCustomerResult execute(UpdateCustomerCommand updateCustomerCommand) {
        log.info("Execute UpdateCustomerUseCase : {}", updateCustomerCommand);

        Customer customer = customerRepository.findById(new CustomerId(updateCustomerCommand.customerId()))
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found: " + updateCustomerCommand.customerId()));

        Email newEmail = new Email(updateCustomerCommand.email());
        if (!newEmail.equals(customer.getEmail()) && customerRepository.existsByEmail(newEmail.value())) {
            throw new CustomerAlreadyExistsException("Email already exists");
        }

        CustomerUpdatedEvent customerUpdatedEvent = customerDomainService.updateCustomer(customer,
                updateCustomerCommand.familyName(),
                updateCustomerCommand.givenName(),
                newEmail,
                customerDataMapper.toPhoneNumber(updateCustomerCommand.phoneNumber()));
        Customer savedCustomer = customerRepository.save(customer);

        log.info("Customer updated with id: {} at {}",
                savedCustomer.getId().value(), customerUpdatedEvent.getUpdatedAt());
        return new UpdateCustomerResult(savedCustomer.getId().value());
    }
}
