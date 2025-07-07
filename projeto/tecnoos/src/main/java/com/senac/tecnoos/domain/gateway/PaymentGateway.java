package com.senac.tecnoos.domain.gateway;

import com.senac.tecnoos.domain.model.Payment;

import java.util.List;

public interface PaymentGateway {

    void save(Payment payment);

    List<Payment> getPayments();

    void update(Payment payment);

    void delete(Long id);
}
