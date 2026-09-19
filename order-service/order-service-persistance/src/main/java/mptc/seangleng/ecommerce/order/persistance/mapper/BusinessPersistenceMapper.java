package mptc.seangleng.ecommerce.order.persistance.mapper;

import mptc.seangleng.ecommerce.order.entity.Business;
import mptc.seangleng.ecommerce.order.persistance.entity.BusinessEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusinessPersistenceMapper {
    Business businessToBusinessEntity(BusinessEntity business);
}
