package com.senac.tecnoos.domain.model;

public class ServiceOrderProduct {

    private Long id;

    private ServiceOrder serviceOrder;

    private Product product;

    public Long getId() {
        return id;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
