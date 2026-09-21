package mptc.seangleng.ecommerce.order.restapi.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mptc.seangleng.ecommerce.order.dto.CreateOrderCommand;
import mptc.seangleng.ecommerce.order.dto.CreateOrderResult;
import mptc.seangleng.ecommerce.order.restapi.dto.OrderCreateRequest;
import mptc.seangleng.ecommerce.order.restapi.dto.OrderCreateResponse;
import mptc.seangleng.ecommerce.order.restapi.mapper.OrderWebMapper;
import mptc.seangleng.ecommerce.order.usecase.CreateOrderUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderCommandController {
    private final CreateOrderUseCase createOrderUseCase;
    private final OrderWebMapper orderWebMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderCreateResponse createOrder(@Valid @RequestBody OrderCreateRequest request) {
        CreateOrderCommand createOrderCommand = orderWebMapper.orderCreateRquesttoCreateOrderCommand(request);
        CreateOrderResult createOrderResult = createOrderUseCase.execute(createOrderCommand);
        return orderWebMapper.orderCreateRequesterCreateOrderResponse(createOrderResult);
    }
}
