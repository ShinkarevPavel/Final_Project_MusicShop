package com.shinkarev.musicshop.util;

import org.testng.Assert;
import org.testng.annotations.*;

public class PasswordHashGeneratorTest {
    public static final String EXPECTED_VALID_VALUE = "VGhpcyBpcyB0aGUgdGVzdA==";
    public static final String EXPECTED_NOT_VALID_VALUE = "VGhpcyBpcyB0aGUgdGVadA==";
    public static final String SOURCE_STRING = "This is the test";

    @Test
    public void testIsHashed() {
        String actual = PasswordHashGenerator.encodePassword(SOURCE_STRING);
        Assert.assertEquals(actual, EXPECTED_VALID_VALUE);
    }
    @Test
    public void testIsNotHashed() {
        String actual = PasswordHashGenerator.encodePassword(SOURCE_STRING);
        Assert.assertNotEquals(actual, EXPECTED_NOT_VALID_VALUE);
    }
}