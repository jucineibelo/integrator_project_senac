package com.senac.tecnoos.adapter.persistence;

import com.senac.tecnoos.domain.gateway.ServiceGateway;
import com.senac.tecnoos.domain.model.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ServiceDao implements ServiceGateway {

    private final Connection connection;

    public ServiceDao(Connection connection) throws Exception {
        this.connection = new ConnectionFactory().getConnection();
    }

    @Override
    public void save(Service service) {
        String sql = "INSERT INTO service (description, price, registration_date) VALUES(?,?, ?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, service.getDescription());
            stmt.setDouble(2, service.getPrice());
            stmt.setDate(3, Date.valueOf(service.getRegistrationDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao tentar salvar no repositório" + e);
        }
    }

    @Override
    public List<Service> getService() {
        return List.of();
    }

    @Override
    public void update(Service service) {

    }

    @Override
    public void delete(Long id) {

    }
}
