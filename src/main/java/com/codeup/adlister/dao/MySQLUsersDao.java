package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        //RECIPE FOR ANY FINDER:

        //write the sql query:
        String sql = "SELECT * FROM users WHERE username = ? LIMIT 1";

        try {
            //create a prepared statement and pass the sql:
            PreparedStatement statement = connection.prepareStatement(sql);
            //bind the parameters:
            statement.setString(1, username);
            //now execute the query and it will return a ResultSet:
            ResultSet resultSet = statement.executeQuery();
            //if there's at least one row, build a User
            if (resultSet.next()) {
                return new User(
                        resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user by username!", e);
        }
    }

    @Override
    public Long insert(User user) {
        //RECIPE FOR THE INSERT

        //write the sql for the insert
        String sql = "INSERT INTO users (username, email, password) " +
                "VALUES (?, ?, ?)";

        try {
            //create a prepared statement with the insert, request the generated keys
            PreparedStatement statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS
            );
            //bind the parameters with their values '?' from the sql query
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            //execute the query
            statement.executeUpdate();

            //return the generated key value
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting a user!", e);
        }
    }

//    private String createInsertQuery(User user) {
//        return "INSERT INTO users(email, password, username) VALUES "
//                + "(" + user.getEmail() + ", "
//                + "'" + user.getPassword() + ", "
//                + "'" + user.getUsername() +"')";
//    }

//    private User extractUsers(ResultSet rs) throws SQLException {
//        return new User(
//                rs.getLong("id"),
//                rs.getString("username"),
//                rs.getString("email"),
//                rs.getString("password")
//        );
//    }
//
//    private List<User> createUsersFromResults(ResultSet rs) throws SQLException {
//        List<User> users = new ArrayList<>();
//        while (rs.next()) {
//            users.add(extractUsers(rs));
//        }
//        return users;
//    }
}
