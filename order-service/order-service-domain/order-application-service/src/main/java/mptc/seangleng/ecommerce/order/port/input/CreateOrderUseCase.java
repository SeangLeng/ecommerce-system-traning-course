package mptc.seangleng.ecommerce.order.port.input;

import mptc.seangleng.ecommerce.order.dto.CreateOrderRequest;

public interface CreateOrderUseCase {
    void execute(CreateOrderRequest createOrderRequest);
}
