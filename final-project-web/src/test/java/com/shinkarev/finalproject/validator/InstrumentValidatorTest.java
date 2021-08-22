package com.shinkarev.finalproject.validator;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.shinkarev.finalproject.validator.InstrumentValidator.*;


public class InstrumentValidatorTest {
    private String namePattern;
    private String brandPattern;
    private String countryPattern;
    private String pricePattern;
    private String descriptionPattern;
    private String ratingPattern;

    @BeforeTest
    public void before() {
        namePattern = INSTRUMENT_NAME.getRegExp();
        brandPattern = INSTRUMENT_BRAND.getRegExp();
        countryPattern = INSTRUMENT_COUNTRY.getRegExp();
        pricePattern = INSTRUMENT_PRICE.getRegExp();
        descriptionPattern = INSTRUMENT_DESCRIPTION.getRegExp();
        ratingPattern = INSTRUMENT_RATING.getRegExp();

    }

    @Test
    public void testGetNameRegExpTrue() {
        final String VALID_NAME = "TestName";
        Assert.assertTrue(VALID_NAME.matches(namePattern));
    }

    @Test
    public void testGetNameRegExpFalse() {
        final String NOT_VALID_NAME = "Test><Name";
        Assert.assertFalse(NOT_VALID_NAME.matches(namePattern));
    }

    @Test
    public void testGetBrandRegExpTrue() {
        final String VALID_BRAND = "TestBrand";
        Assert.assertTrue(VALID_BRAND.matches(brandPattern));
    }

    @Test
    public void testGetBrandRegExpFalse() {
        final String NOT_VALID_NAME = "Test><Brand";
        Assert.assertFalse(NOT_VALID_NAME.matches(brandPattern));
    }

    @Test
    public void testGetCountryRegExpTrue() {
        final String VALID_BRAND = "TestCountry";
        Assert.assertTrue(VALID_BRAND.matches(countryPattern));
    }

    @Test
    public void testGetCountryRegExpFalse() {
        final String NOT_VALID_NAME = "Test><Country";
        Assert.assertFalse(NOT_VALID_NAME.matches(countryPattern));
    }

    @Test
    public void testGetPriceRegExpTrue() {
        final String VALID_PRICE = "500.00";
        Assert.assertTrue(VALID_PRICE.matches(pricePattern));
    }

    @Test
    public void testGetPriceRegExpFalse() {
        final String NOT_VALID_PRICE = "600";
        Assert.assertFalse(NOT_VALID_PRICE.matches(pricePattern));
    }

    @Test
    public void testGetDescriptionRegExpTrue() {
        final String VALID_DESCRIPTION = "He can be large text";
        Assert.assertTrue(VALID_DESCRIPTION.matches(descriptionPattern));
    }

    @Test
    public void testGetDescriptionRegExpFalse() {
        final String NOT_VALID_DESCRIPTION = "He><can be long text";
        Assert.assertFalse(NOT_VALID_DESCRIPTION.matches(descriptionPattern));
    }

    @Test
    public void testGetRatingRegExpTrue() {
        final String VALID_RATING = "5";
        Assert.assertTrue(VALID_RATING.matches(ratingPattern));
    }

    @Test
    public void testGetRatingRegExpFalse() {
        final String NOT_VALID_RATING = "6";
        Assert.assertFalse(NOT_VALID_RATING.matches(ratingPattern));
    }

    @AfterTest
    public void after() {
        namePattern = null;
        brandPattern = null;
        countryPattern = null;
        pricePattern = null;
        descriptionPattern = null;
        ratingPattern = null;
    }
}