package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLUsersDao implements Users {
     Connection connection = null;
    public MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUsername(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }


    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public Long insert(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement(createInsertQuery(user), Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate(createInsertQuery(user), Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new user.", e);
        }
    }

    private String createInsertQuery(User user) {
        return "INSERT INTO users(id, email, password) VALUES "
                + "(" + user.getId() + ", "
                + "'" + user.getEmail() +"', "
                + "'" + user.getPassword() + "')";
    }
}
