package com.senac.tecnoos.adapter.persistence;

import java.sql.Connection;

public interface DataBaseConnectionFactory {
    Connection getConnection() throws Exception;
}
