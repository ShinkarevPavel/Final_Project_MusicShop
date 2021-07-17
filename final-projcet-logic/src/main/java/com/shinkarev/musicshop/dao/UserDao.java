package com.shinkarev.musicshop.dao;

import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<Long, User> {
    boolean block(User user) throws DaoException;

    boolean unblock(User user) throws DaoException;

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    List<User> findUsersByRole(UserRoleType role) throws DaoException;

    List<User> findUsersByStatus(UserStatusType status) throws DaoException;

    boolean addUser(User user, String password) throws DaoException;

    Optional<User> getUserByLogin(String login) throws DaoException;

    Optional<User> getUserPasswordByLogin(String login) throws DaoException;

    Optional<User> findUserByEmail(String email) throws DaoException;
}

