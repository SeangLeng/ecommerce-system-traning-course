package mptc.seangleng.ecommerce.domain.customer.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mptc.seangleng.ecommerce.common_core.PageResponse;
import mptc.seangleng.ecommerce.domain.customer.dto.CustomerResult;
import mptc.seangleng.ecommerce.domain.customer.dto.GetCustomerQuery;
import mptc.seangleng.ecommerce.domain.customer.port.output.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllCustomerUseCase {
    private final CustomerRepository customerRepository;

    public PageResponse<CustomerResult> execute(GetCustomerQuery query) {
        log.info("[GetAllCustomerUseCase] Executing GetAllCustomerUseCase: {}", query);
        return customerRepository.findAll(query.page(),  query.size());
    }
}
