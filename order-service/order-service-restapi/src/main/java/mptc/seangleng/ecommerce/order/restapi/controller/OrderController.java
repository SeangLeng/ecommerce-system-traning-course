package mptc.seangleng.ecommerce.order.restapi.controller;


import jakarta.validation.Valid;
import mptc.seangleng.ecommerce.order.restapi.dto.OrderCreateRequest;
import mptc.seangleng.ecommerce.order.restapi.dto.OrderCreateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderCreateResponse createOrder(@Valid @RequestBody OrderCreateRequest request) {
        return OrderCreateResponse.builder().orderId(UUID.randomUUID()).build();
    }
}
