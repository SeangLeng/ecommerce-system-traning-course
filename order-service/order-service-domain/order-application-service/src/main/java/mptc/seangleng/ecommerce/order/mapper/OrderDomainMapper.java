package mptc.seangleng.ecommerce.order.mapper;

import mptc.seangleng.ecommerce.order.dto.CommandOrderAddress;
import mptc.seangleng.ecommerce.order.dto.CommandOrderItem;
import mptc.seangleng.ecommerce.order.dto.CreateOrderCommand;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.StreetAddress;
import mptc.seangleng.ecommerce.order.entity.Order;
import mptc.seangleng.ecommerce.order.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDomainMapper {

    @Mapping(source = "customerId", target = "customerId.value")
    @Mapping(source = "businessId", target = "businessId.value")
    @Mapping(source = "price", target = "price.amount")
    @Mapping(source = "items", target = "orderItems")
    @Mapping(source = "deliveryAddress", target = "delivery")
    Order createOrderCommandToOrder(CreateOrderCommand orderCommand);

    @Mapping(source = "productId", target = "product.id.value")
    @Mapping(source = "price", target = "price.amount")
    @Mapping(source = "subTotal", target = "subTotal.amount")
    OrderItem commandOrderItemToOrderItem(CommandOrderItem commandOrderItem);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    StreetAddress commandOrderAddressToStreetAddress(CommandOrderAddress address);
}
