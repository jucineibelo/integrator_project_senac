package com.senac.tecnoos.adapter.controller;

import com.senac.tecnoos.adapter.persistence.ConnectionFactory;
import com.senac.tecnoos.adapter.persistence.UserDao;
import com.senac.tecnoos.application.usecase.UserUseCase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.Connection;

public class PrincipalController {

    @FXML
    private StackPane contentPane;

    private UserUseCase userUseCase;

    public PrincipalController() {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.getConnection();
            UserDao userRepository = new UserDao(connection);
            this.userUseCase = new UserUseCase(userRepository);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Falha ao conectar com o banco de dados");
        }
    }

    @FXML
    public void showUserRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/senac/tecnoos/adapter/view/user.fxml"
            ));
            Parent userRegisterScreen = loader.load();

            UserController controller = loader.getController();
            controller.setUserUseCase(userUseCase);

            // Adiciona a tela sobre o contentPane principal
            contentPane.getChildren().add(userRegisterScreen);

        } catch (IOException e) {
            showAlert("Erro", "Falha ao carregar tela de usuário: " + e.getMessage());
        }
    }

    public void returnToMainScreen() {
        contentPane.getChildren().clear();
        // Adicione aqui qualquer conteúdo padrão da tela principal
    }

    @FXML
    public void showProductRegister() {
        showAlert("Informação", "Tela de produtos em desenvolvimento");
    }

    @FXML
    public void showSalesReport() {
        showAlert("Informação", "Tela de relatórios em desenvolvimento");
    }

    @FXML
    public void logout() {
        System.exit(0);
    }

    // Método para mostrar a tela principal (será chamado pelo botão fechar)
    public void showMainScreen() {
        contentPane.getChildren().clear();
        // Aqui você pode adicionar algum conteúdo padrão para a tela principal
        // ou deixar vazio se preferir
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}