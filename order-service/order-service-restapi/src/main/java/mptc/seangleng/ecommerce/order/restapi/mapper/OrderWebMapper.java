package mptc.seangleng.ecommerce.order.restapi.mapper;

import mptc.seangleng.ecommerce.order.dto.CreateOrderCommand;
import mptc.seangleng.ecommerce.order.dto.CreateOrderResult;
import mptc.seangleng.ecommerce.order.restapi.dto.OrderCreateRequest;
import mptc.seangleng.ecommerce.order.restapi.dto.OrderCreateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {
    // source: source bean => OrderCreateRequest
    // target: mapping bean target => CreateOrderCommand
    CreateOrderCommand orderCreateRquesttoCreateOrderCommand(OrderCreateRequest orderCreateRequest);

    OrderCreateResponse orderCreateRequesterCreateOrderResponse(CreateOrderResult orderCreateRequest);
}
