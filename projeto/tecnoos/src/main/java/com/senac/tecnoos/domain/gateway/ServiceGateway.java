package com.senac.tecnoos.domain.gateway;

import com.senac.tecnoos.domain.model.Payment;
import com.senac.tecnoos.domain.model.Service;

import java.util.List;

public interface ServiceGateway {

    void save(Service service);

    List<Service> getService();

    void update(Service service);

    void delete(Long id);
}
