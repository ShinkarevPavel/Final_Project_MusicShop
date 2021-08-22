package com.shinkarev.finalproject.validator;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.shinkarev.finalproject.validator.UserValidator.*;

public class UserValidatorTest {
    private String emailPattern;
    private String loginPattern;
    private String nicknamePattern;
    private String passwordPattern;
    private String namePattern;
    private String surnamePattern;

    @BeforeTest
    public void before() {
        emailPattern = EMAIL.getRegExp();
        loginPattern = LOGIN.getRegExp();
        nicknamePattern = NICKNAME.getRegExp();
        passwordPattern = PASSWORD.getRegExp();
        namePattern = NAME.getRegExp();
        surnamePattern = SURENAME.getRegExp();
    }

    @Test
    public void testGetEmailRegExpTrue() {
        final String VALID_EMAIL = "test@test.com";
        Assert.assertTrue(VALID_EMAIL.matches(emailPattern));
    }

    @Test
    public void testGetEmailRegExpFalse() {
        final String NOT_VALID_EMAIL = "test.com";
        Assert.assertFalse(NOT_VALID_EMAIL.matches(emailPattern));
    }

    @Test
    public void testGetLoginRegExpTrue() {
        final String VALID_LOGIN = "TestLogin2021";
        Assert.assertTrue(VALID_LOGIN.matches(loginPattern));
    }

    @Test
    public void testGetLoginRegExpFalse() {
        final String NOT_VALID_LOGIN = "Test<>Login2021";
        Assert.assertFalse(NOT_VALID_LOGIN.matches(loginPattern));
    }

    @Test
    public void testGetNicknameRegExpTrue() {
        final String VALID_NICKNAME = "CorrectNickname";
        Assert.assertTrue(VALID_NICKNAME.matches(nicknamePattern));
    }

    @Test
    public void testGetNicknameRegExpFalse() {
        final String NOT_VALID_NICKNAME = "Correct<Nickname";
        Assert.assertFalse(NOT_VALID_NICKNAME.matches(nicknamePattern));
    }

    @Test
    public void testGetPasswordRegExpTrue() {
        final String VALID_PASSWORD = "2021CorrectPassword";
        Assert.assertTrue(VALID_PASSWORD.matches(passwordPattern));
    }

    @Test
    public void testGetPasswordRegExpFalse() {
        final String NOT_VALID_PASSWORD = "2021notcorrectpassword";
        Assert.assertFalse(NOT_VALID_PASSWORD.matches(passwordPattern));
    }

    @Test
    public void testGetNameRegExpTrue() {
        final String VALID_NAME = "TestName";
        Assert.assertTrue(VALID_NAME.matches(namePattern));
    }

    @Test
    public void testGetNameRegExpFalse() {
        final String NOT_VALID_NAME = "Test_Name";
        Assert.assertFalse(NOT_VALID_NAME.matches(namePattern));
    }

    @Test
    public void testGetSurnameRegExpTrue() {
        final String VALID_SURNAME = "TestSurname";
        Assert.assertTrue(VALID_SURNAME.matches(surnamePattern));
    }

    @Test
    public void testGetSurnameRegExpFalse() {
        final String NOT_VALID_SURNAME = "Test_Surname";
        Assert.assertFalse(NOT_VALID_SURNAME.matches(surnamePattern));
    }

    @AfterTest
    public void after() {
        emailPattern = null;
        loginPattern = null;
        nicknamePattern = null;
        passwordPattern = null;
        namePattern = null;
        surnamePattern = null;
    }
}