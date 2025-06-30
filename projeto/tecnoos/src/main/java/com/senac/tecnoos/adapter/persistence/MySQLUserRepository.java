package com.senac.tecnoos.adapter.persistence;

import com.senac.tecnoos.domain.gateway.UserGateway;
import com.senac.tecnoos.domain.model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class MySQLUserRepository implements UserGateway {
    private final Connection connection;

    public MySQLUserRepository(Connection connection) throws Exception {
        this.connection = new MySQLConnectionFactory().getConnection();
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO user (name, username, password, registration_date)" +
                "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setDate(4, Date.valueOf(LocalDate.now()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
