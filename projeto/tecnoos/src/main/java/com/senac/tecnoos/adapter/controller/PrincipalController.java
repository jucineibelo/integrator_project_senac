package com.senac.tecnoos.adapter.controller;

import com.senac.tecnoos.adapter.persistence.ConnectionFactory;
import com.senac.tecnoos.adapter.persistence.PaymentDao;
import com.senac.tecnoos.adapter.persistence.ServiceDao;
import com.senac.tecnoos.adapter.persistence.UserDao;
import com.senac.tecnoos.application.usecase.PaymentUseCase;
import com.senac.tecnoos.application.usecase.ServiceUseCase;
import com.senac.tecnoos.application.usecase.UserUseCase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class PrincipalController {

    @FXML
    private StackPane contentPane;

    private UserUseCase userUseCase;

    private PaymentUseCase paymentUseCase;

    private ServiceUseCase serviceUseCase;

    public PrincipalController() {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.getConnection();

            UserDao userRepository = new UserDao(connection);
            PaymentDao paymentDao = new PaymentDao(connection);
            ServiceDao serviceDao = new ServiceDao(connection);


            this.userUseCase = new UserUseCase(userRepository);
            this.paymentUseCase = new PaymentUseCase(paymentDao);
            this.serviceUseCase = new ServiceUseCase(serviceDao);

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
            Parent root = loader.load();

            UserController controller = loader.getController();
            controller.setUserUseCase(userUseCase);

            Stage userStage = new Stage();
            userStage.setTitle("Cadastro de Usuário");
            userStage.setScene(new javafx.scene.Scene(root));
            userStage.initModality(Modality.APPLICATION_MODAL);
            userStage.show();

        } catch (IOException e) {
            showAlert("Erro", "Falha ao carregar tela de usuário: " + e.getMessage());
        }
    }

    @FXML
    public void showPaymentRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/senac/tecnoos/adapter/view/payment.fxml"
            ));
            Parent root = loader.load();

            PaymentController controller = loader.getController();
            controller.setPaymentUseCase(paymentUseCase);

            Stage userStage = new Stage();
            userStage.setTitle("Cadastro de Pagamento");
            userStage.setScene(new javafx.scene.Scene(root));
            userStage.initModality(Modality.APPLICATION_MODAL);
            userStage.show();

        } catch (IOException e) {
            showAlert("Erro", "Falha ao carregar tela de pagamento: " + e.getMessage());
        }
    }

    @FXML
    public void showServiceRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/senac/tecnoos/adapter/view/service.fxml"
            ));
            Parent root = loader.load();

            ServiceController controller = loader.getController();
            controller.setServiceUseCase(serviceUseCase);

            Stage userStage = new Stage();
            userStage.setTitle("Cadastro de Serviço");
            userStage.setScene(new javafx.scene.Scene(root));
            userStage.initModality(Modality.APPLICATION_MODAL);
            userStage.show();

        } catch (IOException e) {
            showAlert("Erro", "Falha ao carregar tela de pagamento: " + e.getMessage());
        }
    }



    public void returnToMainScreen() {
        contentPane.getChildren().clear();
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

    public void showMainScreen() {
        contentPane.getChildren().clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}