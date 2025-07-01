package com.senac.tecnoos.adapter.controller;

import com.senac.tecnoos.application.usecase.CreateUserUseCase;
import com.senac.tecnoos.domain.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UserController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    private CreateUserUseCase createUserUseCase;

    public void setCreateUserUseCase(CreateUserUseCase createUserUseCase){
        this.createUserUseCase = createUserUseCase;
    }

    @FXML
    public void onRegisterButtonClicked(){
        User user = new User();
        user.setName(nameField.getText());
        user.setUsername(usernameField.getText());
        user.setPassword(passwordField.getText());

        createUserUseCase.execute(user);
    }




}
