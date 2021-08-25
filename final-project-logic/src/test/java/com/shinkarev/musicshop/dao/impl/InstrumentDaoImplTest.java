package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.dao.InstrumentDao;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentStatusType;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.DaoException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Optional;

public class InstrumentDaoImplTest {
    private InstrumentDao instrumentDao;
    private long instrumentId;
    private String expected_instrument_name;
    private Instrument instrument;
    private String newBrand;

    @BeforeTest
    public void before() {
        instrumentDao = new InstrumentDaoImpl();
        instrumentId = 19L;
        expected_instrument_name = "Dunlop DEN1056";
        instrument = new Instrument("nameTest", "brandTest", "countryTest", 100.00, 5.0, "testDescription", InstrumentStatusType.AVAILABLE, InstrumentType.GUITARS);
        newBrand = "new BrandTest";
    }

    @Test()
    public void testFindEntityById() throws DaoException {
        Optional<Instrument> optionalInstrument = instrumentDao.findEntityById(instrumentId);
        Assert.assertEquals(optionalInstrument.orElseThrow().getName(), expected_instrument_name);
    }

    @Test(priority = 1)
    public void createTest()  throws DaoException{
        Assert.assertTrue(instrumentDao.create(instrument));
    }

    @Test(priority = 2)
    public void updateTest()  throws DaoException{
        instrument.setBrand(newBrand);
        Assert.assertTrue(instrumentDao.update(instrument));
    }

    @Test(priority = 3)
    public void changeInstrumentStatusTest()  throws DaoException{
        Assert.assertTrue(instrumentDao.changeInstrumentStatusById(instrument.getInstrument_id(), InstrumentStatusType.ON_DEMAND));
    }

    @Test(priority = 4)
    public void changeInstrumentTypeTest()  throws DaoException{
        Assert.assertTrue(instrumentDao.changeInstrumentTypeById(instrument.getInstrument_id(), InstrumentType.OTHER));
    }

    @AfterTest
    public void after() {
        instrumentDao = null;
        expected_instrument_name = null;
        instrument = null;
    }
}