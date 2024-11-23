package model.da;

import model.entity.User;
import model.entity.enums.Role;
import model.tools.CRUD;
import model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import java.sql.*;
        import java.util.ArrayList;
import java.util.List;

public class UserDA implements AutoCloseable, CRUD<User> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public UserDA() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public User save(User user) throws Exception {
        user.setUser_id(ConnectionProvider.getConnectionProvider().getNextId("USER_SEQ"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO USER_TBL (ID, USERNAME, PASSWORD, ROLE) VALUES (?,?,?,?)"
        );
        preparedStatement.setInt(1, user.getUser_id());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, String.valueOf(user.getRole()));
        preparedStatement.execute();
        return user;
    }

    @Override
    public User edit(User user) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE USER_TBL SET USERNAME=?, PASSWORD=?, ENABLED=? WHERE ID=?"
        );
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, String.valueOf(user.getRole()));
        preparedStatement.setInt(4, user.getUser_id());
        preparedStatement.execute();
        return user;
    }

    @Override
    public User remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM USER_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<User> findAll() throws Exception {
        List<User> userList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM USER_TBL ORDER BY ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            User user = User
                    .builder()
                    .user_id(resultSet.getInt("ID"))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .role(Role.valueOf(resultSet.getString("ROLE")))
                    .build();

            userList.add(user);
        }

        return userList;
    }

    @Override
    public User findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM USER_TBL WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = User
                    .builder()
                    .user_id(resultSet.getInt("ID"))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .role(Role.valueOf(resultSet.getString("ROLE")))
                    .build();
        }
        return user;
    }

    public User findByUsername(String username) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM USER_TBL WHERE USERNAME=? ");
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = User
                    .builder()
                    .user_id(resultSet.getInt("ID"))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .role(Role.valueOf(resultSet.getString("ROLE")))
                    .build();
        }
        return user;
    }

    public User findByUsernameAndPassword(String username, String password) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM USER_TBL WHERE USERNAME=? AND PASSWORD=?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = User
                    .builder()
                    .user_id(resultSet.getInt("ID"))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .role(Role.valueOf(resultSet.getString("ROLE")))
                    .build();
        }
        return user;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
