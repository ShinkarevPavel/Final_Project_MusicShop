package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.dao.UserDao;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.pool.ConnectionPool;
import com.shinkarev.musicshop.util.PasswordHashGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.shinkarev.musicshop.dao.impl.SqlQuery.*;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        ResultSet resultSet;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_USERS)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = UserCreator.createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        logger.info(users.size() > 0 ? "Were found " + users.size() + " users" : "Users not found");
        return users;
    }

    @Override
    public Optional<User> findEntityById(Long id) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = UserCreator.createUser(resultSet);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean changeUserStatusById(long userId, UserStatusType statusType) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CONTROL_USER_STATUS)) {
            statement.setString(1, String.valueOf(UserStatusType.ordinal(statusType)));
            statement.setString(2, String.valueOf(userId));
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean changeUserRoleById(long userId, UserRoleType roleType) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CONTROL_USER_ROLE)) {
            statement.setString(1, String.valueOf(UserRoleType.ordinal(roleType)));
            statement.setString(2, String.valueOf(userId));
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean create(User user) throws DaoException {
        throw new UnsupportedOperationException("This method unsupported");
    }

    @Override
    public boolean update(User user) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getNickname());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSurename());
            statement.setInt(6, UserStatusType.ordinal(user.getStatus()));
            statement.setInt(7, UserRoleType.ordinal(user.getRole()));
            statement.setLong(8, user.getId());
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        return rowsUpdate == 1;
    }


    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        User user = null;
        if (login != null && password != null) {
            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_LOGIN_AND_PASSWORD)) {
                statement.setString(1, login);

                statement.setString(2, PasswordHashGenerator.encodePassword(password)); //TODO check DB !!!!!
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    user = UserCreator.createUser(resultSet);
                }
            } catch (SQLException ex) {
                throw new DaoException("Error. Impossible get data from data base.", ex);
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findUsersByRole(UserRoleType role) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ROLE)) {
            statement.setLong(1, UserRoleType.ordinal(role));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = UserCreator.createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        return users;
    }

    @Override
    public List<User> findUsersByStatus(UserStatusType status) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_STATUS)) {
            statement.setLong(1, UserStatusType.ordinal(status));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = UserCreator.createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        return users;
    }

    @Override
    public boolean addUser(User user, String password) throws DaoException {
        boolean flag = false;
        if (user != null && password != null) {
            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getLogin());
                statement.setString(2, PasswordHashGenerator.encodePassword(password));
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getNickname());
                statement.setString(5, user.getName());
                statement.setString(6, user.getSurename());
                statement.setInt(7, UserStatusType.ordinal(user.getStatus()));
                statement.setInt(8, UserRoleType.ordinal(user.getRole()));
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                    flag = true;
                }
            } catch (SQLException ex) {
                throw new DaoException("Error. Impossible add user to data base.", ex);
            }
        }
        return flag;
    }

    @Override
    public Optional<User> getUserByLogin(String login) throws DaoException {
        User user = null;
        if (login != null) {
            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)){
                statement.setString(1, login);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    user = UserCreator.createUser(resultSet);
                }
            } catch (SQLException ex) {
                throw new DaoException("Error. Impossible get user from data base.", ex);
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getUserPasswordByLogin(String login) throws DaoException{
        User user = null;
        if (login != null) {
            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL_GET_USER_PASSWORD)){
                statement.setString(1, login);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    user = UserCreator.createUser(resultSet);
                }
            } catch (SQLException ex) {
                throw new DaoException("Error. Impossible get data from data base.", ex);
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        User user = null;
        if (email != null) {
            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL)){
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    user = UserCreator.createUser(resultSet);
                }
            } catch (SQLException ex) {
                throw new DaoException("Error. Impossible get user from data base.", ex);
            }
        }
        return Optional.ofNullable(user);
    }
}
