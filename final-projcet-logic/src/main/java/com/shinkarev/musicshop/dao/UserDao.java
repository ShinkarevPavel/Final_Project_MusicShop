package com.shinkarev.musicshop.dao;

import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<Long, User> {

    /**
     * @return quantity of {@link User}s into data base
     * @throws DaoException if the request to data base could not be handled
     */

    int getUserCount() throws DaoException;

    /**
     * @param page current page on the client side
     * @return {@link User} collection on the current page
     * @throws DaoException if the request to data base could not be handled
     */

    List<User> findByPage(int page) throws DaoException;

    /**
     * @param userId     {@link User}s id, which  status should be changed
     * @param statusType current status
     * @return true if status was changed, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean changeUserStatusById(long userId, UserStatusType statusType) throws DaoException;

    /**
     * @param userId   {@link User}s id, which  status should be changed
     * @param roleType current role
     * @return true if role was changed, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean changeUserRoleById(long userId, UserRoleType roleType) throws DaoException;

    /**
     * @param login    {@link User}s login
     * @param password {@link User}s password
     * @return Optional <{@link User}>
     * @throws DaoException if the request to data base could not be handled
     */

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

//    List<User> findUsersByRole(UserRoleType role) throws DaoException;
//
//    List<User> findUsersByStatus(UserStatusType status) throws DaoException;


    /**
     * @param user            current {@link User}
     * @param password        {@link User}s password
     * @param registrationKey {@link User}s registration key
     * @return tru if {@link User} was added to data base, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */
    boolean addUser(User user, String password, String registrationKey) throws DaoException;

    /**
     * @param login {@link User}s login
     * @return Optional <{@link User}>
     * @throws DaoException if the request to data base could not be handled
     */

    Optional<User> getUserByLogin(String login) throws DaoException;


//    Optional<User> getUserPasswordByLogin(String login) throws DaoException;

    /**
     * @param email {@link User}s email
     * @return Optional <{@link User}>
     * @throws DaoException if the request to data base could not be handled
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * @param nickname {@link User}s nickname
     * @return Optional <{@link User}>
     * @throws DaoException if the request to data base could not be handled
     */

    Optional<User> findUserByNickname(String nickname) throws DaoException;

    /**
     * @param registrationKey {@link User}s registration key that used for registration confirmation
     * @return Optional <{@link User}>
     * @throws DaoException if the request to data base could not be handled
     */

    Optional<User> findUserByRegistrationKey(String registrationKey) throws DaoException;

    /**
     * @param userId   {@link User}s id for which will be changed password
     * @param password {@link User}s password
     * @return true if password was changed, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean changePassword(long userId, String password) throws DaoException;
}

