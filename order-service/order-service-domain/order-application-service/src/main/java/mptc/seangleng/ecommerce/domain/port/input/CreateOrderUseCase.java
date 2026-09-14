package mptc.seangleng.ecommerce.domain.port.input;

import mptc.seangleng.ecommerce.domain.port.dto.CreateOrderRequest;

public interface CreateOrderUseCase {
    void execute(CreateOrderRequest createOrderRequest);
}
