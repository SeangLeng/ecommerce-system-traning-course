package mptc.seangleng.ecommerce.payment.mapper;

import mptc.seangleng.ecommerce.payment.dto.CreatePaymentCommand;
import mptc.seangleng.ecommerce.payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentDomainMapper {
    @Mapping(source = "orderId", target = "orderId.value")
    @Mapping(source = "customerId", target = "customerId.value")
    @Mapping(source = "price", target = "price.amount")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paymentStatus", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Payment createPaymentCommandToPayment(CreatePaymentCommand createPaymentCommand);
}
