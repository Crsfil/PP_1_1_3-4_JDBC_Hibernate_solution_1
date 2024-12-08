package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/users_from_kata_work";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database");
            return connection;
        } catch (SQLException e) {
            System.err.println("Failed to connect to database");
            throw new RuntimeException("Error connecting to the database: " + e.getMessage(), e);
        }
    }
}
