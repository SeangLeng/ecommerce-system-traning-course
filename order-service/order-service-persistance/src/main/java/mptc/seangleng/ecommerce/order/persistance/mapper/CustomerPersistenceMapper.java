package mptc.seangleng.ecommerce.order.persistance.mapper;

import mptc.seangleng.ecommerce.order.entity.Customer;
import mptc.seangleng.ecommerce.order.persistance.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerPersistenceMapper {
    @Mapping(source = "id", target = "id.value")
    Customer customerEntityToCustomer(CustomerEntity customerEntity);
}
