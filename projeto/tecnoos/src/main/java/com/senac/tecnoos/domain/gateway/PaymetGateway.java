package com.senac.tecnoos.domain.gateway;

import com.senac.tecnoos.domain.model.Payment;

public interface PaymetGateway {
    void save(Payment payment);
}
