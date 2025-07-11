package com.senac.tecnoos.adapter.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/base";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
