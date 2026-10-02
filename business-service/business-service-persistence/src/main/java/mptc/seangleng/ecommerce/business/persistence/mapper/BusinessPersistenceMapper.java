package mptc.seangleng.ecommerce.business.persistence.mapper;

import mptc.seangleng.ecommerce.business.entity.Business;
import mptc.seangleng.ecommerce.business.entity.OrderDetail;
import mptc.seangleng.ecommerce.business.entity.Product;
import mptc.seangleng.ecommerce.business.persistence.entity.BusinessEntity;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.BusinessId;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.Money;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.ProductId;
import mptc.seangleng.ecommerce.persistence.business.exception.BusinessPersistenceException;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BusinessPersistenceMapper {

    default List<UUID> businessToBusinessProducts(Business business) {
        return business.getOrderDetail().getProducts().stream()
                .map(product -> product.getId().value())
                .toList();
    }

    default Business businessEntityToBusiness(List<BusinessEntity> businessEntities) {
        BusinessEntity businessEntity = businessEntities.stream()
                .findFirst()
                .orElseThrow(() -> new BusinessPersistenceException("Business could not be found"));

        List<Product> products = businessEntities.stream()
                .map(entity -> Product.builder()
                        .id(new ProductId(entity.getProductId()))
                        .name(entity.getProductName())
                        .price(new Money(entity.getProductPrice()))
                        .available(Boolean.TRUE.equals(entity.getProductAvailable()))
                        .build())
                .toList();

        return Business.builder()
                .id(new BusinessId(businessEntity.getBusinessId()))
                .active(Boolean.TRUE.equals(businessEntity.getBusinessActive()))
                .orderDetail(OrderDetail.builder().products(products).build())
                .build();
    }
}
