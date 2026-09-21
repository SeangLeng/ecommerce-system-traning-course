package mptc.seangleng.ecommerce.order.usecase;

import lombok.extern.slf4j.Slf4j;
import mptc.seangleng.ecommerce.order.dto.CreateOrderCommand;
import mptc.seangleng.ecommerce.order.dto.CreateOrderResult;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CreateOrderUseCase {
    public CreateOrderResult execute(CreateOrderCommand createOrderCommand) {
        log.info("Execute CreateOrderUseCase {}", createOrderCommand);

        // validate customer


        return new CreateOrderResult(UUID.randomUUID());
    }
}
