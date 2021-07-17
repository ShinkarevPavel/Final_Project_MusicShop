package com.shinkarev.musicshop.service;

import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String login, String password);
    boolean isLoginUnique(String login);
    boolean isEmailUnique(String email);
    List<User> getAllUsers() throws ServiceException;
}
