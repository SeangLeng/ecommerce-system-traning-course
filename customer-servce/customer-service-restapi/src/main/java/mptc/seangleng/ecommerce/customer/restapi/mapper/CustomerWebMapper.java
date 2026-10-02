package mptc.seangleng.ecommerce.customer.restapi.mapper;

import mptc.seangleng.ecommerce.customer.restapi.dto.CustomerCreateRequest;
import mptc.seangleng.ecommerce.customer.restapi.dto.CustomerCreateResponse;
import mptc.seangleng.ecommerce.customer.restapi.dto.CustomerUpdateRequest;
import mptc.seangleng.ecommerce.customer.restapi.dto.CustomerUpdateResponse;
import mptc.seangleng.ecommerce.domain.customer.dto.CreateCustomerCommand;
import mptc.seangleng.ecommerce.domain.customer.dto.CreateCustomerResult;
import mptc.seangleng.ecommerce.domain.customer.dto.UpdateCustomerCommand;
import mptc.seangleng.ecommerce.domain.customer.dto.UpdateCustomerResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CustomerWebMapper {
    CreateCustomerCommand customerCreateRequestToCreateCustomerCommand(CustomerCreateRequest customerCreateRequest);

    CustomerCreateResponse createCustomerResultToCustomerCreateResponse(CreateCustomerResult createCustomerResult);

    @Mapping(source = "customerId", target = "customerId")
    UpdateCustomerCommand customerUpdateRequestToUpdateCustomerCommand(UUID customerId, CustomerUpdateRequest customerUpdateRequest);

    CustomerUpdateResponse updateCustomerResultToCustomerUpdateResponse(UpdateCustomerResult updateCustomerResult);
}
