package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.dao.UserDao;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.DaoException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Optional;

public class UserDaoImplTest {
    private UserDao userDao;
    private final long USER_ID = 1;
    private final String USER_LOGIN = "Pavel209";
    private final String USER_EMAIL = "pave209@gmal.com";
    private final String USER_NICKNAME = "vodoopavel";
    private Optional<User> expectedUser;
    private Optional<User> actualUser;

    @BeforeTest
    public void before() {
        userDao = new UserDaoImpl();
        expectedUser = Optional.of(new User(1L, "Pavel209", "pave209@gmal.com", "vodoopavel","Pavel", "Shinkarev", UserStatusType.ACTIVE, UserRoleType.ADMIN));

    }

    @Test
    public void testFindEntityById() throws DaoException {
        actualUser = userDao.findEntityById(USER_ID);
        Assert.assertSame(actualUser, expectedUser);
    }

    @Test
    public void testGetUserByLogin() throws DaoException{
        actualUser = userDao.getUserByLogin(USER_LOGIN);
        Assert.assertSame(actualUser, expectedUser);
    }

    @Test
    public void testFindUserByEmail() throws DaoException{
        actualUser = userDao.findUserByEmail(USER_EMAIL);
        Assert.assertSame(actualUser, expectedUser);
    }

    @Test
    public void testFindUserByNickname() throws DaoException{
        actualUser = userDao.findUserByNickname(USER_NICKNAME);
        Assert.assertSame(actualUser, expectedUser);
    }

    @AfterTest
    public void after() {
        userDao = null;
        expectedUser = Optional.empty();
        actualUser = Optional.empty();
    }
}