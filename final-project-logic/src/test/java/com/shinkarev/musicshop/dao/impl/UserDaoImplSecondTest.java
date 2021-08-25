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

public class UserDaoImplSecondTest {
    private UserDao userDao;
    private User user;
    private String password;
    private String registrationKey;
    private String userSurname;
    private String emailToken;
    private String newPassword;

    @BeforeTest
    public void before() {
        userDao = new UserDaoImpl();
        user = new User("Mr.Test", "test.test@gmal.com", "mr_Test","Test", "Test", UserStatusType.ACTIVE, UserRoleType.CLIENT);
        password = "password";
        registrationKey = "registrationKey";
        userSurname = "YellowTest";
        emailToken = user.getEmail();
        newPassword = "newPassword";
    }

    @Test
    public void addUserTest() throws DaoException {
        Assert.assertTrue(userDao.addUser(user, password, registrationKey));
    }

    @Test(priority = 1)
    public void findUserByNicknameTest() throws DaoException{
        Optional<User> optionalUser = userDao.findUserByNickname(user.getNickname());
        user.setId(optionalUser.orElseThrow().getId());
        Assert.assertEquals(user, optionalUser.orElseThrow());
    }

    @Test(priority = 4)
    public void changeUserStatusByIdTest() throws DaoException{
        Assert.assertTrue(userDao.changeUserStatusById(user.getId(), UserStatusType.BLOCKED));
    }

    @Test(priority = 5)
    public void changeUserRoleByIdTest() throws DaoException{
        Assert.assertTrue(userDao.changeUserRoleById(user.getId(), UserRoleType.ADMIN));
    }

    @Test(priority = 6)
    public void updateTest() throws DaoException{
        user.setSurename(userSurname);
        Assert.assertTrue(userDao.update(user));
    }

    @Test(priority = 2)
    public void getUserByLoginTest() throws DaoException{
        Optional<User> optionalUser = userDao.getUserByLogin(user.getLogin());
        Assert.assertEquals(user, optionalUser.orElseThrow());
    }

    @Test(priority = 3)
    public void getUserByEmail() throws DaoException {
        Optional<User> optionalUser = userDao.findUserByEmail(user.getEmail());
        Assert.assertEquals(user, optionalUser.orElseThrow());
    }


    @Test(priority = 8)
    public void setEmailTokenTest() throws DaoException {
        Assert.assertTrue(userDao.setEmailTokenByEmail(user.getEmail(), emailToken));
    }

    @Test(priority = 9)
    public void changePasswordTest() throws DaoException {
        Assert.assertTrue(userDao.changePassword(user.getId(), newPassword));
    }

    @Test(priority = 10)
    public void getUserIdByEmailToken() throws DaoException {
        long expected = user.getId();
        long actual = userDao.getUserIdByEmailToken(emailToken);
        Assert.assertEquals(actual, expected);
    }

    @AfterTest
    public void after() {
        userDao = null;
        user = null;
        password = null;
        registrationKey = null;
        userSurname = null;
        emailToken = null;
        newPassword = null;
    }
}
