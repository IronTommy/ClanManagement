package com.example.clanmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnectionManager {

    private static final Logger logger = Logger.getLogger(DatabaseConnectionManager.class.getName());
    private static final String URL = "jdbc:mysql://localhost:3306/schema_clan";
    private static final String USER = "root";
    private static final String PASSWORD = "pass";

    public static Connection createConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.log(Level.INFO, "Database connection created successfully.");
            return connection;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to create a database connection.", e);
            throw new RuntimeException("Failed to create a database connection.", e);
        }
    }
}
