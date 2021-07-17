package com.shinkarev.musicshop.service.impl;

import com.shinkarev.musicshop.dao.impl.UserDaoImpl;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Override
    public Optional<User> login(String login, String password) {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = null;
        try {
            Optional<User> currentUser = userDao.findUserByLoginAndPassword(login, password);
            if (currentUser.isPresent()) {
                user = currentUser.get();
            }
        } catch (DaoException e) {
//            TODO
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean isLoginUnique(String login) {
        UserDaoImpl userDao = new UserDaoImpl();
        boolean isPresent = false;
        try {
            Optional<User> currentUser = userDao.getUserByLogin(login);
            if (!currentUser.isPresent()) {
                isPresent = true;
            }
        } catch (DaoException e) {
            // TODO service EX
        }
        return isPresent;
    }

    @Override
    public boolean isEmailUnique(String email) {
        UserDaoImpl userDao = new UserDaoImpl();
        boolean isUnique = false;
        try {
            Optional<User> currentUser = userDao.findUserByEmail(email);
            if (!currentUser.isPresent()) {
                isUnique = true;
            }
        } catch (DaoException e) {
            // TODO service EX
        }
        return isUnique;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> users;
        try {
            users = userDao.findAll();
        } catch (DaoException ex) {
            throw new ServiceException("Error getting users", ex);
        }
        return users;
    }
}
