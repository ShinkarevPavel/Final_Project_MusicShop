package com.shinkarev.finalproject.validator;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OrderValidatorTest {
    private String addressPattern;

    @BeforeTest
    public void before() {
        addressPattern = OrderValidator.ADDRESS.getRegEx();
    }

    @Test
    public void testGetAddressRegExTrue() {
        final String VALID_ADDRESS = "city Test, str. Test building 4/152";
        Assert.assertTrue(VALID_ADDRESS.matches(addressPattern));
    }

    @Test
    public void testGetAddressRegExFalse() {
        final String NOT_VALID_ADDRESS = "city <Test>, str. Test building 4/152";
        Assert.assertFalse(NOT_VALID_ADDRESS.matches(addressPattern));
    }

    @AfterTest
    public void after() {
        addressPattern = null;
    }
}