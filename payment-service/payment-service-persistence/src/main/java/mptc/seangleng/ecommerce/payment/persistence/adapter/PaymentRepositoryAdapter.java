package mptc.seangleng.ecommerce.payment.persistence.adapter;

import lombok.RequiredArgsConstructor;
import mptc.seangleng.ecommerce.payment.entity.Payment;
import mptc.seangleng.ecommerce.payment.persistence.entity.PaymentEntity;
import mptc.seangleng.ecommerce.payment.persistence.mapper.PaymentPersistenceMapper;
import mptc.seangleng.ecommerce.payment.persistence.repository.PaymentJpaRepository;
import mptc.seangleng.ecommerce.payment.port.output.PaymentRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentPersistenceMapper paymentPersistenceMapper;

    @Override
    public Payment savePayment(Payment payment) {
        PaymentEntity paymentEntity = paymentPersistenceMapper.paymentToPaymentEntity(payment);
        PaymentEntity savedPaymentEntity = paymentJpaRepository.save(paymentEntity);
        return paymentPersistenceMapper.paymentEntityToPayment(savedPaymentEntity);
    }
}
