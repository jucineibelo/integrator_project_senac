package com.senac.tecnoos.application.usecase;

import com.senac.tecnoos.domain.gateway.ServiceGateway;
import com.senac.tecnoos.domain.model.Service;

import java.time.LocalDate;
import java.util.List;

public class ServiceUseCase {

    private final ServiceGateway serviceGateway;

    public ServiceUseCase(ServiceGateway serviceGateway) {
        this.serviceGateway = serviceGateway;
    }

    public void save(Service service) {
        service.setRegistrationDate(LocalDate.now());
        serviceGateway.save(service);
    }

    public List<Service> getService() {
        return serviceGateway.getService();
    }

    public void update(Service service) {
        serviceGateway.update(service);
    }

    public void delete(Long id) {
        serviceGateway.delete(id);
    }
}
