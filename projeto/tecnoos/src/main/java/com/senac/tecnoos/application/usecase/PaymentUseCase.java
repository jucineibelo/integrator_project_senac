package com.senac.tecnoos.application.usecase;

import com.senac.tecnoos.domain.gateway.PaymentGateway;
import com.senac.tecnoos.domain.model.Payment;
import java.util.List;

public class PaymentUseCase {

    private final PaymentGateway paymentGateway;

    public PaymentUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void save(Payment payment) {
        paymentGateway.save(payment);
    }

    public List<Payment> getPayments() {
        return paymentGateway.getPayments();
    }

    public void update(Payment payment) {
        paymentGateway.update(payment);
    }

    public void delete(Long id) {
        paymentGateway.delete(id);
    }
}
