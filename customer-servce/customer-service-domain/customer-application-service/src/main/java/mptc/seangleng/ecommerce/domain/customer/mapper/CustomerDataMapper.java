package mptc.seangleng.ecommerce.domain.customer.mapper;

import mptc.seangleng.ecommerce.domain.customer.dto.CreateCustomerCommand;
import mptc.seangleng.ecommerce.domain.entity.Customer;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.Email;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.PhoneNumber;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerDataMapper {
    // id and status are not set here: the domain sets them in initiateCustomer()
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "loyaltyTier", ignore = true)
    Customer createCustomerCommandToCustomer(CreateCustomerCommand createCustomerCommand);

    default Email toEmail(String email) {
        return email == null ? null : new Email(email);
    }

    default PhoneNumber toPhoneNumber(String phoneNumber) {
        return phoneNumber == null ? null : new PhoneNumber(phoneNumber);
    }
}
