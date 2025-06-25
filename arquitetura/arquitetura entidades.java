 1. Client.java (Entidade - Camada de Domínio)
 
 

package domain.entities;

import java.time.LocalDate;

public class Client {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private LocalDate registrationDate;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }
}



✅ 2. ClientRepository.java (Interface - Camada de Domínio)



package domain.repositories;

import domain.entities.Client;

public interface ClientRepository {
    void save(Client client);
}




✅ 3. CreateClientUseCase.java (Caso de Uso - Camada de Aplicação)

package application.usecases;

import domain.entities.Client;
import domain.repositories.ClientRepository;

import java.time.LocalDate;

public class CreateClientUseCase {
    private final ClientRepository repository;

    public CreateClientUseCase(ClientRepository repository) {
        this.repository = repository;
    }

    public void execute(Client client) {
        client.setRegistrationDate(LocalDate.now());
        repository.save(client);
    }
}




✅ 4. MySQLClientRepository.java (Implementação - Camada de Infraestrutura)

package adapters.persistence;

import domain.entities.Client;
import domain.repositories.ClientRepository;

import java.sql.*;

public class MySQLClientRepository implements ClientRepository {
    private final Connection connection;

    public MySQLClientRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Client client) {
        String sql = "INSERT INTO client (name, address, phone, registration_date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getAddress());
            stmt.setString(3, client.getPhone());
            stmt.setDate(4, Date.valueOf(client.getRegistrationDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




✅ 5. ClientController.java (Camada de Interface)

package adapters.controllers;

import application.usecases.CreateClientUseCase;
import domain.entities.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ClientController {

    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField phoneField;

    private CreateClientUseCase createClientUseCase;

    public void setCreateClientUseCase(CreateClientUseCase useCase) {
        this.createClientUseCase = useCase;
    }

    @FXML
    public void onRegisterButtonClicked() {
        Client client = new Client();
        client.setName(nameField.getText());
        client.setAddress(addressField.getText());
        client.setPhone(phoneField.getText());

        createClientUseCase.execute(client);
    }
}





✅ 6. client.fxml (Interface Gráfica JavaFX)

<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<VBox xmlns:fx="http://javafx.com/fxml" fx:controller="adapters.controllers.ClientController" spacing="10" padding="20">
    <Label text="Cadastro de Cliente"/>
    
    <TextField fx:id="nameField" promptText="Nome"/>
    <TextField fx:id="addressField" promptText="Endereço"/>
    <TextField fx:id="phoneField" promptText="Telefone"/>
    
    <Button text="Cadastrar" onAction="#onRegisterButtonClicked"/>
</VBox>




✅ 7. App.java (Inicialização do JavaFX)

package main;

import adapters.controllers.ClientController;
import adapters.persistence.MySQLClientRepository;
import application.usecases.CreateClientUseCase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/base", "usuario", "senha");

        MySQLClientRepository repo = new MySQLClientRepository(conn);
        CreateClientUseCase useCase = new CreateClientUseCase(repo);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client.fxml"));
        Scene scene = new Scene(loader.load());

        ClientController controller = loader.getController();
        controller.setCreateClientUseCase(useCase);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Cadastro de Cliente");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}





📌 Notas Finais

    Substitua "usuario" e "senha" pelas credenciais corretas do seu MySQL.

    Crie os pacotes conforme a estrutura sugerida.

    Você pode adicionar testes para o CreateClientUseCase sem depender de banco, usando um repositório em memória (InMemoryClientRepository).