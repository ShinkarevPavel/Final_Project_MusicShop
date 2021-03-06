package com.shinkarev.musicshop.service.impl;

import com.shinkarev.musicshop.dao.UserDao;
import com.shinkarev.musicshop.dao.impl.UserDaoImpl;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static UserService instance;
    private UserDao userDao = new UserDaoImpl();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        boolean result;
        try {
            result = userDao.update(user);
        } catch (DaoException ex) {
            throw new ServiceException("Error. Impossible update user", ex);
        }
        return result;
    }

    @Override
    public int getUserCount() throws ServiceException {
        try {
            return userDao.getUserCount();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with users count .", e);
            throw new ServiceException("Error with users count .", e);
        }
    }

    @Override
    public List<User> readByPage(int page) throws ServiceException {
        List<User> users;
        try {
            users = userDao.findByPage(page);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find all Users .", e);
            throw new ServiceException("Error with find all Users .", e);
        }
        return users;
    }

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        try {
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException("Error with find all Users .", e);
        }
    }

    @Override
    public boolean isLoginUnique(String login) throws ServiceException {
        boolean isPresent = false;
        try {
            Optional<User> currentUser = userDao.getUserByLogin(login);
            if (!currentUser.isPresent()) {
                isPresent = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Error Login checking", ex);
        }
        return isPresent;
    }

    @Override
    public boolean isEmailUnique(String email) throws ServiceException {
        boolean isUnique = false;
        try {
            Optional<User> currentUser = userDao.findUserByEmail(email);
            if (!currentUser.isPresent()) {
                isUnique = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Error getting data from Dao", ex);
        }
        return isUnique;
    }

    @Override
    public List<User> getAllEntity() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAll();
        } catch (DaoException ex) {
            throw new ServiceException("Error getting users", ex);
        }
        return users;
    }

    @Override
    public boolean userStatusController(long userId, UserStatusType statusType) throws ServiceException {
        boolean isChanged = false;
        try {
            Optional<User> optionalUser = userDao.findEntityById(userId);
            if (optionalUser.isPresent()) {
                userDao.changeUserStatusById(userId, statusType);
                isChanged = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Impossible change status for user", ex);
        }
        return isChanged;
    }

    @Override
    public boolean userRoleController(long userId, UserRoleType roleType) throws ServiceException {
        boolean isChanged = false;
        try {
            Optional<User> optionalUser = userDao.findEntityById(userId);
            if (optionalUser.isPresent()) {
                userDao.changeUserRoleById(userId, roleType);
                isChanged = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Impossible change role for user", ex);
        }
        return isChanged;
    }

    @Override
    public Optional<User> getUserById(long userId) throws ServiceException {
        try {
            return userDao.findEntityById(userId);
        } catch (DaoException ex) {
            throw new ServiceException("Impossible get user with " + userId, ex);
        }
    }

    @Override
    public Optional<User> getUserByNickName(String nickname) throws ServiceException {
        try {
            return userDao.findUserByNickname(nickname);
        } catch (DaoException ex) {
            throw new ServiceException("Impossible get user with " + nickname, ex);
        }
    }

    @Override
    public boolean addUser(User user, String password, String registrationKey) throws ServiceException {
        boolean isAdded = false;
        try {
            if (userDao.addUser(user, password, registrationKey)) {
                isAdded = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Impossible add user into DB", ex);
        }
        return isAdded;
    }

    @Override
    public Optional<User> getUserByRegistrationKey(String registrationKey) throws ServiceException {
        try {
            return userDao.findUserByRegistrationKey(registrationKey);
        } catch (DaoException ex) {
            throw new ServiceException("Impossible get user", ex);
        }
    }

    @Override
    public boolean changePassword(long userId, String password) throws ServiceException {
        boolean result;
        try {
            result = userDao.changePassword(userId, password);
        } catch (DaoException ex) {
            throw new ServiceException("Impossible change password", ex);
        }
        return result;
    }

    @Override
    public boolean setEmailToken(String email, String token) throws ServiceException {
        boolean result;
        try {
            result = userDao.setEmailTokenByEmail(email, token);
        } catch (DaoException ex) {
            throw new ServiceException("Impossible set email token", ex);
        }
        return result;
    }

    @Override
    public Long getUserIdByEmailToken(String key) throws ServiceException {
        try {
            return userDao.getUserIdByEmailToken(key);
        } catch (DaoException ex) {
            throw new ServiceException("Impossible get userId by email token", ex);
        }
    }
}
