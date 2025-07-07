package com.senac.tecnoos.adapter.controller;

import com.senac.tecnoos.application.usecase.PaymentUseCase;
import com.senac.tecnoos.domain.model.Payment;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static com.senac.tecnoos.util.AlertUtil.showAlert;

public class PaymentController {

    @FXML
    private TextField descricaoField;

    private PaymentUseCase paymentUseCase;

    public void setPaymentUseCase(PaymentUseCase paymentUseCase){
        this.paymentUseCase = paymentUseCase;
    }

    @FXML
    public void onRegisterButtonClicked(){
        try {
            if (descricaoField.getText().isEmpty()){
                showAlert("Erro", "Todos os campos devem ser preenchidos");
                return;
            }
            Payment payment = new Payment();
            payment.setDescription(descricaoField.getText());
            paymentUseCase.save(payment);
            showAlert("Sucesso", "Pagamento cadastrado com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        descricaoField.clear();
    }

}
