package mptc.seangleng.ecommerce.order.persistance.mapper;

import mptc.seangleng.ecommerce.order.entity.Order;
import mptc.seangleng.ecommerce.order.entity.OrderItem;
import mptc.seangleng.ecommerce.order.persistance.entity.OrderEntity;
import mptc.seangleng.ecommerce.order.persistance.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderPersistenceMapper {
    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "customerId.value", target = "customerId")
    @Mapping(source = "businessId.value", target = "businessId")
    @Mapping(source = "price.amount", target = "price")
    @Mapping(source = "trackingId.value", target = "trackId")
    @Mapping(source = "failureMessages", target = "failureMessage", qualifiedByName = "mapFailureMessages")
    @Mapping(source = "orderItems", target = "items")
    OrderEntity orderToOrderEntity(Order order);

    @Named("mapFailureMessages")
    default String mapFailureMessages(List<String> failureMessages) {
        return String.join(",", failureMessages);
    }

    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "product.id.value", target = "productId")
    @Mapping(source = "price.amount", target = "price")
    @Mapping(source = "subTotal.amount", target = "subTotal")
    OrderItemEntity orderItemEntityToOrderItem(OrderItem orderItem);

    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "customerId.value", source = "customerId")
    @Mapping(target = "businessId.value", source = "businessId")
    @Mapping(target = "price.amount", source = "price")
    @Mapping(target = "trackingId.value", source = "trackId")
    @Mapping(target = "failureMessages", source = "failureMessage", qualifiedByName = "mapFailureMessagesToList")
    @Mapping(target = "orderItems", source = "items")
    Order orderEntityToOrder(OrderEntity orderEntity);

    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "product.id.value", source = "productId")
    @Mapping(target = "price.amount", source = "price")
    @Mapping(target = "subTotal.amount", source = "subTotal")
    OrderItem orderItemToOrderItemEntity(OrderItemEntity orderItemEntity);

    @Named("mapFailureMessagesToList")
    default List<String> mapFailureMessagesToList(String failureMessages) {
        return Arrays.stream(failureMessages.split(",")).toList();
    }
}
