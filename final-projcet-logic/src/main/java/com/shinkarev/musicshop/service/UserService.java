package com.shinkarev.musicshop.service;

import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    int getUserCount() throws ServiceException;

    List<User> readByPage(int page) throws ServiceException;

    Optional<User> login(String login, String password) throws ServiceException;

    boolean isLoginUnique(String login) throws ServiceException;

    boolean isEmailUnique(String email) throws ServiceException;

    boolean userStatusController(long userId, UserStatusType statusType) throws ServiceException;

    boolean userRoleController(long userId, UserRoleType roleType) throws ServiceException;

    Optional<User> getUserById(long userId) throws ServiceException;

    Optional<User> getUserByNickName(String nickname) throws ServiceException;

    boolean addUser(User user, String password, String registrationKey) throws ServiceException;

    Optional<User> getUserByRegistrationKey(String registrationKey) throws ServiceException;

    List<User> getAllEntity() throws ServiceException;

    boolean changePassword(long userId, String password) throws ServiceException;

    boolean updateUser(User user) throws ServiceException;

    boolean setEmailToken(String email, String token) throws ServiceException;

    Long getUserIdByEmailToken(String key) throws ServiceException;
}
