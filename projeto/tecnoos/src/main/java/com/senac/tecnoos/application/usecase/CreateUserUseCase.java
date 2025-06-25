package com.senac.tecnoos.application.usecase;

import com.senac.tecnoos.domain.gateway.UserGateway;
import com.senac.tecnoos.domain.model.User;

import java.time.LocalDate;

public class CreateUserUseCase {
    private final UserGateway userGateway;

    public CreateUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public void execute(User user){
        user.setRegistrationDate(LocalDate.now());
        userGateway.save(user);
    }
}
