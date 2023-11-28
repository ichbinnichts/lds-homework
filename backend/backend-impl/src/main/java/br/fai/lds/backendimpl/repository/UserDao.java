package br.fai.lds.backendimpl.repository;

import br.fai.lds.backendimpl.repository.connection.ConnectionFactory;
import br.fai.lds.backendusecases.port.UserRepository;
import br.fai.lds.domain.user.UserModel;
import br.fai.lds.domain.user.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserRepository {
    @Override
    public UserModel findUserById(final int id) {
        final UserModel user = new UserModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        final String sql = "SELECT id, username, password, role "+
                " FROM usuario WHERE id = ?; ";

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();


            if(resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setUserRole(UserRole.valueOf(resultSet.getString("role")));

                resultSet.close();
                preparedStatement.close();
            }else{
                return null;
            }

            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<UserModel> findAllUsers() {
        final List<UserModel> userList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        final String sql = "SELECT id, username, password, role "+
                " FROM usuario; ";

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            UserModel user = new UserModel();

            while(resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setUserRole(UserRole.valueOf(resultSet.getString("role")));

                userList.add(user);


            }
            resultSet.close();
            preparedStatement.close();

            return userList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserModel findUserByUsername(String username) {
        final UserModel user = new UserModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        final String sql = "SELECT id, username, password, role "+
                " FROM usuario WHERE username = ?; ";

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();


            if(resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setUserRole(UserRole.valueOf(resultSet.getString("role")));

                resultSet.close();
                preparedStatement.close();
            }else{
                return null;
            }

            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserModel login(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM usuario ";
        sql += " WHERE ";
        sql += " username = ? AND password = ? ;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            final UserModel userModel;
            if (resultSet.next()) {
                userModel = new UserModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setUsername(resultSet.getString("username"));
                userModel.setUserRole(UserRole.valueOf(resultSet.getString("role")));


            } else {
                userModel = null;
            }

            resultSet.close();
            preparedStatement.close();

            return userModel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
