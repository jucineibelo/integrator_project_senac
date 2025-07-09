package com.senac.tecnoos.adapter.persistence;

import com.senac.tecnoos.domain.gateway.PaymentGateway;
import com.senac.tecnoos.domain.model.Payment;

import java.sql.*;
import java.util.ArrayList;
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
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT id, description, registration_date FROM payment";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getLong("id"));
                payment.setDescription(rs.getString("description"));
                payment.setRegistrationDate(rs.getDate("registration_date").toLocalDate());

                payments.add(payment);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pagamentos: " + e);
        }

        return payments;
    }

    @Override
    public void update(Payment payment) {
        String sql = "UPDATE payment SET description = ? where id = ?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, payment.getDescription());
            stmt.setLong(2, payment.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao tentar atualizar pagamento" + e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM payment where id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao tentar excluir registro" + e);
        }
    }
}
