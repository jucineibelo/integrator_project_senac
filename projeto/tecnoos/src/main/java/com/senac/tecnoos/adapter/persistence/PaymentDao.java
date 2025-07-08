package com.senac.tecnoos.adapter.persistence;

import com.senac.tecnoos.domain.gateway.PaymentGateway;
import com.senac.tecnoos.domain.model.Payment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PaymentDao implements PaymentGateway {
    private final Connection connection;

    public PaymentDao(Connection connection) throws Exception {
        this.connection = new ConnectionFactory().getConnection();
    }

    @Override
    public void save(Payment payment) {
        String sql = "INSERT INTO payment (description, registration_date) VALUES(?,?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, payment.getDescription());
            stmt.setDate(2, Date.valueOf(payment.getRegistrationDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao tentar salvar no repositório" + e);
        }
    }

    @Override
    public List<Payment> getPayments() {
        return List.of();
    }

    @Override
    public void update(Payment payment) {

    }

    @Override
    public void delete(Long id) {

    }
}
