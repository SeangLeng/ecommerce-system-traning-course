package mptc.seangleng.ecommerce.payment.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mptc.seangleng.ecommerce.payment.dto.CreatePaymentCommand;
import mptc.seangleng.ecommerce.payment.dto.CreatePaymentResult;
import mptc.seangleng.ecommerce.payment.entity.CreditEntry;
import mptc.seangleng.ecommerce.payment.entity.CreditHistory;
import mptc.seangleng.ecommerce.payment.entity.Payment;
import mptc.seangleng.ecommerce.payment.event.PaymentEvent;
import mptc.seangleng.ecommerce.payment.exception.PaymentDomainException;
import mptc.seangleng.ecommerce.payment.mapper.PaymentDomainMapper;
import mptc.seangleng.ecommerce.payment.port.output.CreditEntryRepository;
import mptc.seangleng.ecommerce.payment.port.output.CreditHistoryRepository;
import mptc.seangleng.ecommerce.payment.port.output.PaymentRepository;
import mptc.seangleng.ecommerce.payment.service.PaymentDomainService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreatePaymentUseCase {
    private final PaymentDomainService paymentDomainService;
    private final PaymentRepository paymentRepository;
    private final PaymentDomainMapper paymentDomainMapper;
    private final CreditEntryRepository creditEntryRepository;
    private final CreditHistoryRepository creditHistoryRepository;

    @Transactional
    public CreatePaymentResult execute(CreatePaymentCommand createPaymentCommand) {
        log.info("executing CreatePaymentUseCase: {}", createPaymentCommand);

        Payment payment = paymentDomainMapper.createPaymentCommandToPayment(createPaymentCommand);

        CreditEntry creditEntry = creditEntryRepository.findByCustomerId(payment.getCustomerId());
        if (creditEntry == null) {
            throw new PaymentDomainException("Could not find credit entry for customer: "
                    + payment.getCustomerId().value());
        }

        List<CreditHistory> creditHistories = new ArrayList<>(
                creditHistoryRepository.findByCustomerId(payment.getCustomerId()));
        int historyCountBefore = creditHistories.size();

        PaymentEvent paymentEvent = paymentDomainService.validateAndInitiatePayment(
                payment, creditEntry, creditHistories);

        Payment savePayment = paymentRepository.savePayment(payment);
        if (savePayment == null) {
            throw new PaymentDomainException("Could not save payment into Database");
        }

        if (paymentEvent.getFailureMessages().isEmpty()) {
            creditEntryRepository.save(creditEntry);
            if (creditHistories.size() > historyCountBefore) {
                creditHistoryRepository.save(creditHistories.getLast());
            }
        }

        log.info("Payment {} processed for customer {} with status {}",
                savePayment.getId().value(),
                payment.getCustomerId().value(),
                savePayment.getPaymentStatus());

        return new CreatePaymentResult(savePayment.getId().value(), savePayment.getPaymentStatus());
    }
}
