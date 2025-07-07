package com.senac.tecnoos.adapter.controller;

import com.senac.tecnoos.application.usecase.UserUseCase;
import com.senac.tecnoos.domain.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.senac.tecnoos.util.AlertUtil.showAlert;

public class UserController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    private UserUseCase userUseCase;

    public void setUserUseCase(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @FXML
    public void onRegisterButtonClicked() {
        try {
            if (nameField.getText().isEmpty() || usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                showAlert("Erro", "Todos os campos devem ser preenchidos");
                return;
            }

            User user = new User();
            user.setName(nameField.getText());
            user.setUsername(usernameField.getText());
            user.setPassword(passwordField.getText());

            userUseCase.insert(user);
            showAlert("Sucesso", "Usuário cadastrado com sucesso");

            clearFields();

        } catch (Exception e) {
            showAlert("Erro", "Falha ao cadastrar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void closeWindow() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }

    private void clearFields() {
        nameField.clear();
        usernameField.clear();
        passwordField.clear();
    }
}