package com.senac.tecnoos.adapter.controller;

import com.senac.tecnoos.application.usecase.UserUseCase;
import com.senac.tecnoos.domain.model.User;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class UserController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private StackPane rootPane;

    private UserUseCase userUseCase;


    public void setUserUseCase(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @FXML
    public void onRegisterButtonClicked() {
        try {
            // Validação dos campos
            if (nameField.getText().isEmpty() || usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                showAlert("Erro", "Todos os campos devem ser preenchidos");
                return;
            }

            // Cria e salva o usuário
            User user = new User();
            user.setName(nameField.getText());
            user.setUsername(usernameField.getText());
            user.setPassword(passwordField.getText());

            userUseCase.insert(user);
            showAlert("Sucesso", "Usuário cadastrado com sucesso");

            // Limpa os campos após o cadastro
            clearFields();

        } catch (Exception e) {
            showAlert("Erro", "Falha ao cadastrar usuário: " + e.getMessage());
            e.printStackTrace(); // Debug
        }
    }

    @FXML
    public void closeWindow() {
        // Pega a janela atual associada a qualquer componente da tela
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close(); // Fecha apenas esta janela
    }

    @FXML
    private void closeCurrentWindow(ActionEvent event) {
        // Obtém o nó que disparou o evento (o botão Cancelar)
        Node source = (Node) event.getSource();

        // Obtém o Stage (janela) atual
        Stage currentStage = (Stage) source.getScene().getWindow();

        // Fecha apenas esta janela
        currentStage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Método para limpar os campos do formulário
    private void clearFields() {
        nameField.clear();
        usernameField.clear();
        passwordField.clear();
    }
}