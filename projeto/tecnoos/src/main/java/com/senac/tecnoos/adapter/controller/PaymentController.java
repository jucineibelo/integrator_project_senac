package com.senac.tecnoos.adapter.controller;

import com.senac.tecnoos.application.usecase.PaymentUseCase;
import com.senac.tecnoos.domain.model.Payment;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

import static com.senac.tecnoos.util.AlertUtil.showAlert;

public class PaymentController {

    @FXML
    private TextField descricaoField;

    @FXML
    private TableView<Payment> tabelaPagamentos;

    @FXML
    private TableColumn<Payment, Long> colunaCodigo;

    @FXML
    private TableColumn<Payment, String> colunaDescricao;

    @FXML
    private TableColumn<Payment, LocalDate> colunaData;

    private Payment pagamentoSelecionado;

    private PaymentUseCase paymentUseCase;

    public void setPaymentUseCase(PaymentUseCase paymentUseCase) {
        this.paymentUseCase = paymentUseCase;
        carregarPagamentos();
    }

    @FXML
    public void onRegisterButtonClicked() {
        try {
            if (descricaoField.getText().isEmpty()) {
                showAlert("Erro", "A descrição não pode estar vazia");
                return;
            }

            if (pagamentoSelecionado == null) {
                Payment novoPagamento = new Payment();
                novoPagamento.setDescription(descricaoField.getText());
                paymentUseCase.save(novoPagamento);
                showAlert("Sucesso", "Pagamento cadastrado com sucesso!");
            } else {
                pagamentoSelecionado.setDescription(descricaoField.getText());
                paymentUseCase.update(pagamentoSelecionado);
                showAlert("Sucesso", "Pagamento atualizado com sucesso!");
            }

            clearFields();
            carregarPagamentos();

        } catch (Exception e) {
            showAlert("Erro", "Falha ao salvar/atualizar pagamento: " + e.getMessage());
        }
    }

    @FXML
    public void onDeleteButtonClicked() {
        Payment selecionado = tabelaPagamentos.getSelectionModel().getSelectedItem();

        if (selecionado == null) {
            showAlert("Erro", "Selecione um pagamento para excluir");
            return;
        }

        paymentUseCase.delete(selecionado.getId());
        showAlert("Sucesso", "Pagamento excluído com sucesso");

        clearFields();
        carregarPagamentos();
    }


    @FXML
    public void onEditButtonClicked() {
        pagamentoSelecionado = tabelaPagamentos.getSelectionModel().getSelectedItem();

        if (pagamentoSelecionado == null) {
            showAlert("Erro", "Selecione um pagamento para editar");
            return;
        }

        descricaoField.setText(pagamentoSelecionado.getDescription());
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
        colunaData.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
    }

    private void carregarPagamentos() {
        List<Payment> lista = paymentUseCase.getPayments();
        tabelaPagamentos.getItems().setAll(lista);
    }


    private void clearFields() {
        descricaoField.clear();
        pagamentoSelecionado = null;
    }

}
