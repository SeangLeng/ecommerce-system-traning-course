package mptc.seangleng.ecommerce.order.persistance.mapper;

import mptc.seangleng.ecommerce.order.ecommerce.valueobject.StreetAddress;
import mptc.seangleng.ecommerce.order.entity.Order;
import mptc.seangleng.ecommerce.order.entity.OrderItem;
import mptc.seangleng.ecommerce.order.persistance.entity.OrderAddressEntity;
import mptc.seangleng.ecommerce.order.persistance.entity.OrderEntity;
import mptc.seangleng.ecommerce.order.persistance.entity.OrderItemEntity;
import org.mapstruct.*;

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
    @Mapping(source = "delivery", target = "streetAddress")
    @Mapping(target = "newEntity", ignore = true)
    OrderEntity orderToOrderEntity(Order order);

    @Named("mapFailureMessages")
    default String mapFailureMessages(List<String> failureMessages) {
        return failureMessages == null ? "" : String.join(",", failureMessages);
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
    @Mapping(target = "delivery", source = "streetAddress")
    Order orderEntityToOrder(OrderEntity orderEntity);

    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "product.id.value", source = "productId")
    @Mapping(target = "price.amount", source = "price")
    @Mapping(target = "subTotal.amount", source = "subTotal")
    OrderItem orderItemToOrderItemEntity(OrderItemEntity orderItemEntity);

    @Mapping(source = "pastaCode", target = "postalCode")
    @Mapping(target = "orderEntity", ignore = true)
    OrderAddressEntity streetAddressToEntity(StreetAddress address);

    @Mapping(source = "postalCode", target = "pastaCode")
    StreetAddress entityToStreetAddress(OrderAddressEntity entity);

    @Named("mapFailureMessagesToList")
    default List<String> mapFailureMessagesToList(String failureMessages) {
        return failureMessages == null ? List.of("") : List.of(failureMessages);
    }

    @AfterMapping
    default void linkAddress(@MappingTarget OrderEntity orderEntity) {
        if (orderEntity.getStreetAddress() != null) {
            orderEntity.getStreetAddress().setOrderEntity(orderEntity);
        }
        if (orderEntity.getItems() != null) {
            orderEntity.getItems().forEach(item -> item.setOrder(orderEntity));
        }
    }
}
