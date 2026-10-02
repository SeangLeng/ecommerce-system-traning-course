package mptc.seangleng.ecommerce.payment.port.output;

import mptc.seangleng.ecommerce.order.ecommerce.valueobject.CustomerId;
import mptc.seangleng.ecommerce.payment.entity.CreditEntry;

public interface CreditEntryRepository {
    CreditEntry findByCustomerId(CustomerId customerId);

    CreditEntry save(CreditEntry creditEntry);
}
