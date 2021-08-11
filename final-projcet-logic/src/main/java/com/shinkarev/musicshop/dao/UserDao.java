package com.shinkarev.musicshop.dao;

import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<Long, User> {

    int getUserCount() throws DaoException;

    List<User> findByPage(int page) throws DaoException;

    boolean changeUserStatusById(long userId, UserStatusType statusType) throws DaoException;

    boolean changeUserRoleById(long userId, UserRoleType roleType) throws DaoException;

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    List<User> findUsersByRole(UserRoleType role) throws DaoException;

    List<User> findUsersByStatus(UserStatusType status) throws DaoException;

    boolean addUser(User user, String password, String registrationKey) throws DaoException;

    Optional<User> getUserByLogin(String login) throws DaoException;

    Optional<User> getUserPasswordByLogin(String login) throws DaoException;

    Optional<User> findUserByEmail(String email) throws DaoException;

    Optional<User> findUserByNickname(String nickname) throws DaoException;

    Optional<User> findUserByRegistrationKey(String registrationKey) throws DaoException;
}

