package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.dao.UserDao;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.pool.ConnectionPool;
import com.shinkarev.musicshop.util.PasswordHashGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.shinkarev.musicshop.dao.impl.SqlQuery.*;

/**
 *  The {@link UserDaoImpl} class provides access to
 *  users table in the database
 */


public class UserDaoImpl implements UserDao {
    private static Logger logger = LogManager.getLogger();

    @Override
    public int getUserCount() throws DaoException {
        return rowCountByQuery(SQL_GET_ALL_USERS);
    }


    @Override
    public List<User> findByPage(int page) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(buildPageableQuery(SQL_GET_ALL_USERS + USER_ORDER_BY, page))) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = UserCreator.createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error with find all Users.", ex);
            throw new DaoException("Error with find all Users .", ex);
        }
        return users;
    }

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
            logger.log(Level.ERROR, "Error. Impossible get data from data base.", ex);
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean changeUserStatusById(long userId, UserStatusType statusType) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CONTROL_USER_STATUS)) {
            statement.setInt(1, UserStatusType.ordinal(statusType));
            statement.setLong(2, userId);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error. Impossible change status into data base.", ex);
            throw new DaoException("Error. Impossible change status into data base.", ex);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean changeUserRoleById(long userId, UserRoleType roleType) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CONTROL_USER_ROLE)) {
            statement.setInt(1, UserRoleType.ordinal(roleType));
            statement.setLong(2, userId);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error. Impossible change role into data base.", ex);
            throw new DaoException("Error. Impossible change role into data base.", ex);
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
            logger.log(Level.ERROR, "Error. Impossible change role into data base.", ex);
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
                statement.setString(2, PasswordHashGenerator.encodePassword(password));
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    user = UserCreator.createUser(resultSet);
                }
            } catch (SQLException ex) {
                logger.log(Level.ERROR, "Error. Impossible change role into data base.", ex);
                throw new DaoException("Error. Impossible get data from data base.", ex);
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean addUser(User user, String password, String registrationKey) throws DaoException {
        boolean isAdded = false;
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
                statement.setString(9, registrationKey);
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                    isAdded = true;
                }
            } catch (SQLException ex) {
                logger.log(Level.ERROR, "Error. Impossible add user to data base.", ex);
                throw new DaoException("Error. Impossible add user to data base.", ex);
            }
        }
        return isAdded;
    }

    @Override
    public Optional<User> getUserByLogin(String login) throws DaoException {
        return getUser(login, SQL_FIND_USER_BY_LOGIN);
    }


    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        return getUser(email, SQL_FIND_USER_BY_EMAIL);
    }

    @Override
    public Optional<User> findUserByNickname(String nickname) throws DaoException {
        return getUser(nickname, SQL_FIND_USER_BY_NICKNAME);
    }

    private Optional<User> getUser(String parameter, String sqlQuery) throws DaoException {
        User user = null;
        if (parameter != null) {
            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, parameter);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    user = UserCreator.createUser(resultSet);
                }
            } catch (SQLException ex) {
                logger.log(Level.ERROR, "Error. Impossible get user from data base.", ex);
                throw new DaoException("Error. Impossible get user from data base.", ex);
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByRegistrationKey(String registrationKey) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_REGISTRATION_KEY)) {
            statement.setString(1, registrationKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = UserCreator.createUser(resultSet);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error. Impossible get data from data base.", ex);
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean changePassword(long userId, String password) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_USER_CHANGE_PASSWORD)) {
            statement.setString(1, PasswordHashGenerator.encodePassword(password));
            statement.setLong(2, userId);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error. Impossible change password.", ex);
            throw new DaoException("Error. Impossible change password.", ex);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean setEmailTokenByEmail(String email, String token) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SET_EMAIL_TOKEN)) {
            statement.setString(1, token);
            statement.setString(2, email);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error. Impossible set token.", ex);
            throw new DaoException("Error. Impossible set token.", ex);
        }
        return rowsUpdate == 1;
    }

    @Override
    public Long getUserIdByEmailToken(String key) throws DaoException {
        Long userId = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_USER_ID_BY_TOKEN)) {
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getLong(UserField.ID);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error. Impossible set token.", ex);
            throw new DaoException("Error. Impossible set token.", ex);
        }
        return userId;
    }
}
