package mptc.seangleng.ecommerce.order.port.output;

import mptc.seangleng.ecommerce.order.entity.Business;

import java.util.Optional;
import java.util.UUID;

public interface BusinessRepository {
    Optional<Business> findById(UUID id);
}
