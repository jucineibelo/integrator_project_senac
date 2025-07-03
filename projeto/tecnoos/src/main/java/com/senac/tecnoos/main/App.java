package com.senac.tecnoos.main;

import com.senac.tecnoos.adapter.controller.UserController;
import com.senac.tecnoos.adapter.persistence.MySQLConnectionFactory;
import com.senac.tecnoos.adapter.persistence.MySQLUserRepository;
import com.senac.tecnoos.application.usecase.CreateUserUseCase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class App extends Application {

    private final MySQLConnectionFactory conn = new MySQLConnectionFactory();

    @Override
    public void start(Stage stage) throws Exception {
        Connection connection = conn.getConnection();
        MySQLUserRepository userRepository = new MySQLUserRepository(connection);
        CreateUserUseCase userUseCase = new CreateUserUseCase(userRepository);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/senac/tecnoos/adapter/view/user.fxml"));
        Scene scene = new Scene(loader.load());

        UserController controller = loader.getController();
        controller.setCreateUserUseCase(userUseCase);

        stage.setScene(scene);
        stage.setTitle("Cadastro de Usuário");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
