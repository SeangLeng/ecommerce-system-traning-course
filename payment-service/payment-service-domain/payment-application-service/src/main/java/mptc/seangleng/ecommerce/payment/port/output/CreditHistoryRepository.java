package mptc.seangleng.ecommerce.payment.port.output;

import mptc.seangleng.ecommerce.order.ecommerce.valueobject.CustomerId;
import mptc.seangleng.ecommerce.payment.entity.CreditHistory;

import java.util.List;

public interface CreditHistoryRepository {
    CreditHistory save(CreditHistory creditHistory);

    List<CreditHistory> findByCustomerId(CustomerId customerId);
}
