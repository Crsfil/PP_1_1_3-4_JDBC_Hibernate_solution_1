package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(100), " +
                "lastName VARCHAR(100), " +
                "age INT" +
                ");";
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
            System.out.println("Table 'users' created successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error creating the table", e);
        }
    }


    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users;";
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
            System.out.println("Table 'users' dropped successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error dropping the table", e);
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?);";
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("User " + name + " " + lastName + " added successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user", e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?;";
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("User with ID " + id + " removed successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error removing user", e);
        }
    }


    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users;";
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all users", e);
        }
        return users;
    }


    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users;";
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
            System.out.println("Table 'users' cleaned successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error cleaning the table", e);
        }
    }

}
