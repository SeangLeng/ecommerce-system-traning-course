package mptc.seangleng.ecommerce.order.port.input;

import mptc.seangleng.ecommerce.order.dto.CreateOrderCommand;

public interface CreateOrderUseCase {
    void execute(CreateOrderCommand createOrderCommand);
}
