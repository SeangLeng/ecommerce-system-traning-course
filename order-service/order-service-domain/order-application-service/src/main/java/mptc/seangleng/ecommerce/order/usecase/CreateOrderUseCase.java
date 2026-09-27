package mptc.seangleng.ecommerce.order.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mptc.seangleng.ecommerce.order.dto.CreateOrderCommand;
import mptc.seangleng.ecommerce.order.dto.CreateOrderResult;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.BusinessId;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.Money;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.ProductId;
import mptc.seangleng.ecommerce.order.entity.Business;
import mptc.seangleng.ecommerce.order.entity.Order;
import mptc.seangleng.ecommerce.order.entity.Product;
import mptc.seangleng.ecommerce.order.event.OrderCreatedEvent;
import mptc.seangleng.ecommerce.order.exception.OrderDomainException;
import mptc.seangleng.ecommerce.order.mapper.OrderDomainMapper;
import mptc.seangleng.ecommerce.order.port.output.BusinessRepository;
import mptc.seangleng.ecommerce.order.port.output.CustomerRepository;
import mptc.seangleng.ecommerce.order.port.output.OrderRepository;
import mptc.seangleng.ecommerce.order.service.OrderDomainService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateOrderUseCase {
    private final OrderDomainService orderDomainService;

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BusinessRepository businessRepository;
    private final OrderDomainMapper orderDomainMapper;

//    @Transactional
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

        business = businessRepository.findBusiness(business).orElseThrow(() -> new OrderDomainException("Could not find business"));

        log.info("Found business {}", business);

        // Invoke order domain logic
        Order order = orderDomainMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, business);
        log.info("Initiate order {}", orderCreatedEvent.getOrder().getId());

        // save to database, and repository.
        Order saveOrder = orderRepository.saveOrder(order);

        if (saveOrder == null) {
            throw new OrderDomainException("Could not save order");
        }

        return new CreateOrderResult(saveOrder.getId().value());
    }
}
