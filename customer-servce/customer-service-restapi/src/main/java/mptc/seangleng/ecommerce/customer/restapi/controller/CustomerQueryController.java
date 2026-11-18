package mptc.seangleng.ecommerce.customer.restapi.controller;

import lombok.RequiredArgsConstructor;
import mptc.seangleng.ecommerce.common_core.PageResponse;
import mptc.seangleng.ecommerce.customer.client.dto.UserResponse;
import mptc.seangleng.ecommerce.customer.client.feing.UserFeignClient;
import mptc.seangleng.ecommerce.customer.restapi.dto.CustomerResponse;
import mptc.seangleng.ecommerce.customer.restapi.mapper.CustomerWebMapper;
import mptc.seangleng.ecommerce.domain.customer.dto.CustomerResult;
import mptc.seangleng.ecommerce.domain.customer.dto.GetCustomerQuery;
import mptc.seangleng.ecommerce.domain.customer.port.output.CustomerRepository;
import mptc.seangleng.ecommerce.domain.customer.usecase.GetAllCustomerUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerQueryController {
    private final UserFeignClient userFeignClient;
    private final GetAllCustomerUseCase getAllCustomerUseCase;
    private final CustomerWebMapper customerWebMapper;

    @GetMapping
    public PageResponse<CustomerResult> getAllCustomers(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ) {
        PageResponse<CustomerResult> result = getAllCustomerUseCase.execute(new GetCustomerQuery(page, size));
        return customerWebMapper.toCustomerResponse(result);
    }

    @GetMapping("/check-user/{id}")
    UserResponse checkUser(@PathVariable String id) {
        return userFeignClient.getUserById(id);
    }
}
