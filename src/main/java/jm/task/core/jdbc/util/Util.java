package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/users_from_kata_work";
    private static final String USER = "root"; // или имя вашего пользователя
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

    SessionFactory getSessionFactory() {
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.url", URL);
        config.setProperty("hibernate.connection.username", USER);
        config.setProperty("hibernate.connection.password", PASSWORD);
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "update");
        config.setProperty("hibernate.show_sql", "true");

        config.addAnnotatedClass(User.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties());

        return config.buildSessionFactory(builder.build());
    }
}
