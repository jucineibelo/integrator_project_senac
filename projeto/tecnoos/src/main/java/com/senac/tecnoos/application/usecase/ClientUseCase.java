package com.senac.tecnoos.application.usecase;

import com.senac.tecnoos.domain.gateway.ClientGateway;

public class ClientUseCase {

    private final ClientGateway clientGateway;

    public ClientUseCase(ClientGateway clientGateway) {
        this.clientGateway = clientGateway;
    }
}
