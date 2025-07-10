package com.senac.tecnoos.adapter.controller;

import com.senac.tecnoos.application.usecase.ServiceUseCase;
import com.senac.tecnoos.domain.model.Service;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

import static com.senac.tecnoos.util.AlertUtil.showAlert;

public class ServiceController {

    @FXML private TextField descricaoField;
    @FXML private TextField precoField;

    @FXML private TableView<Service> tabelaServicos;
    @FXML private TableColumn<Service, Long> colunaCodigo;
    @FXML private TableColumn<Service, String> colunaDescricao;
    @FXML private TableColumn<Service, Double> colunaPrice;
    @FXML private TableColumn<Service, LocalDate> colunaData;

    private Service serviceSelecionado;
    private ServiceUseCase serviceUseCase;

    public void setServiceUseCase(ServiceUseCase serviceUseCase) {
        this.serviceUseCase = serviceUseCase;
        carregarService();
    }

    @FXML
    public void onRegisterButtonClicked() {
        try {
            String descricao = descricaoField.getText();
            String precoTexto = precoField.getText();

            if (descricao.isEmpty() || precoTexto.isEmpty()) {
                showAlert("Erro", "Todos os campos devem ser preenchidos.");
                return;
            }

            double preco = Double.parseDouble(precoTexto);

            if (serviceSelecionado == null) {
                Service novo = new Service();
                novo.setDescription(descricao);
                novo.setPrice(preco);
                serviceUseCase.save(novo);
                showAlert("Sucesso", "Serviço cadastrado!");
            } else {
                serviceSelecionado.setDescription(descricao);
                serviceSelecionado.setPrice(preco);
                serviceUseCase.update(serviceSelecionado);
                showAlert("Sucesso", "Serviço atualizado!");
            }

            clearFields();
            carregarService();

        } catch (NumberFormatException e) {
            showAlert("Erro", "O preço deve ser numérico.");
        } catch (Exception e) {
            showAlert("Erro", "Erro ao salvar: " + e.getMessage());
        }
    }

    @FXML
    public void onDeleteButtonClicked() {
        Service selecionado = tabelaServicos.getSelectionModel().getSelectedItem();

        if (selecionado == null) {
            showAlert("Erro", "Selecione um serviço para excluir.");
            return;
        }

        serviceUseCase.delete(selecionado.getId());
        showAlert("Sucesso", "Serviço excluído.");
        clearFields();
        carregarService();
    }

    @FXML
    public void onEditButtonClicked() {
        serviceSelecionado = tabelaServicos.getSelectionModel().getSelectedItem();

        if (serviceSelecionado == null) {
            showAlert("Erro", "Selecione um serviço para editar.");
            return;
        }

        descricaoField.setText(serviceSelecionado.getDescription());
        precoField.setText(String.valueOf(serviceSelecionado.getPrice()));
    }

    @FXML
    public void closeWindow() {
        Stage stage = (Stage) descricaoField.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("description"));
        colunaPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
    }

    private void carregarService() {
        List<Service> lista = serviceUseCase.getService();
        tabelaServicos.getItems().setAll(lista);
    }

    private void clearFields() {
        descricaoField.clear();
        precoField.clear();
        serviceSelecionado = null;
    }

}
