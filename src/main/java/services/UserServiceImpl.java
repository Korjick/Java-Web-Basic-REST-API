package services;

import models.User;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private DataSource dataSource;
    private final static String SQL_GET_ALL = "select * from users;";
    private final static String SQL_GET_USER_BY_NAME = "select * from users where name=? and age=? and \"isMarried\"=?;";

    private final static String SQL_DELETE_ALL_USERS = "delete from users;";
    private final static String SQL_DELETE_USER = "delete from users where name=? and age=? and \"isMarried\"=?;";

    private final static String SQL_INSERT_USER = "insert into users (name, age, \"isMarried\") values (?, ?, ?);";

    private final static String SQL_UPDATE_USER = "update users set name = ?, age = ?, \"isMarried\" = ? where name = ? and age = ? and \"isMarried\" = ?;";

    public UserServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> getUsers() {
        Connection connection = null;
        PreparedStatement statement = null;
        List<User> allUsers = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GET_ALL);

            try (ResultSet setOfAllUsers = statement.executeQuery()) {
                while (setOfAllUsers.next()) {
                    User user = new User(
                            setOfAllUsers.getString("name"),
                            setOfAllUsers.getInt("age"),
                            setOfAllUsers.getBoolean("isMarried"));
                    allUsers.add(user);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
            }
        }
        return allUsers;
    }

    @Override
    public Optional<User> getUser(User user) {

        Connection connection = null;
        PreparedStatement statement = null;
        Optional<User> getUser = Optional.empty();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GET_USER_BY_NAME);
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setBoolean(3, user.getMarried());

            try (ResultSet isUser = statement.executeQuery()) {
                if (isUser.next()) {
                    User tmp = new User(
                            isUser.getString("name"),
                            isUser.getInt("age"),
                            isUser.getBoolean("isMarried"));
                    getUser = Optional.of(tmp);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
            }
        }

        return getUser;
    }

    @Override
    public void deleteUser(User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_USER);
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setBoolean(3, user.getMarried());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Problem with deleting users");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
            }
        }
    }

    @Override
    public void deleteUsers() {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_ALL_USERS);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Problem with deleting users");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
            }
        }
    }

    @Override
    public void putUser(User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_INSERT_USER);
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setBoolean(3, user.getMarried());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Problem with insert user");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
            }
        }
    }

    @Override
    public void updateUser(User oldUser, User newUser) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1, newUser.getName());
            statement.setInt(2, newUser.getAge());
            statement.setBoolean(3, newUser.getMarried());
            statement.setString(4, oldUser.getName());
            statement.setInt(5, oldUser.getAge());
            statement.setBoolean(6, oldUser.getMarried());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Problem with update user");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
            }
        }
    }
}
