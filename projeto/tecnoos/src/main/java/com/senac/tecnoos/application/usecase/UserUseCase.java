package com.senac.tecnoos.application.usecase;

import com.senac.tecnoos.domain.gateway.UserGateway;
import com.senac.tecnoos.domain.model.User;

import java.time.LocalDate;

public class UserUseCase {
    private final UserGateway userGateway;

    public UserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public void insert(User user){
        user.setRegistrationDate(LocalDate.now());
        userGateway.save(user);
    }
}
