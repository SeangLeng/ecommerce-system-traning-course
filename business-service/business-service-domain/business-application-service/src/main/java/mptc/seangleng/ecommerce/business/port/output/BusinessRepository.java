package mptc.seangleng.ecommerce.business.port.output;

import mptc.seangleng.ecommerce.business.entity.Business;

import java.util.Optional;

public interface BusinessRepository {
    Optional<Business> findBusinessInformation(Business business);
}
