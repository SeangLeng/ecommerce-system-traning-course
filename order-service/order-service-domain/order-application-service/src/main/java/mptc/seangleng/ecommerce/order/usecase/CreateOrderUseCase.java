package mptc.seangleng.ecommerce.order.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mptc.seangleng.ecommerce.order.dto.CreateOrderCommand;
import mptc.seangleng.ecommerce.order.dto.CreateOrderResult;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.BusinessId;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.Money;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.ProductId;
import mptc.seangleng.ecommerce.order.entity.Business;
import mptc.seangleng.ecommerce.order.entity.Product;
import mptc.seangleng.ecommerce.order.exception.OrderDomainException;
import mptc.seangleng.ecommerce.order.port.output.BusinessRepository;
import mptc.seangleng.ecommerce.order.port.output.CustomerRepository;
import mptc.seangleng.ecommerce.order.port.output.OrderRepository;
import mptc.seangleng.ecommerce.order.service.OrderDomainService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateOrderUseCase {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BusinessRepository businessRepository;

    public CreateOrderResult execute(CreateOrderCommand createOrderCommand) {
        log.info("Execute CreateOrderUseCase {}", createOrderCommand);

        // validate customer
        customerRepository.findById(createOrderCommand.customerId())
                .orElseThrow(() -> new OrderDomainException("Could not find customer with ID" + createOrderCommand.customerId()));

        List<Product> products = createOrderCommand.items().stream().map(
                commandOrderItem -> Product
                        .builder()
                        .id(new ProductId(commandOrderItem.productId()))
                        .price(new Money(commandOrderItem.price()))
                        .build()
        ).toList();

        Business business = Business.builder()
                .id(new BusinessId(createOrderCommand.businessId()))
                .products(products)
                .build();

        log.info("Create order use case executed with business {}", business);
        return new CreateOrderResult(UUID.randomUUID());
    }
}
