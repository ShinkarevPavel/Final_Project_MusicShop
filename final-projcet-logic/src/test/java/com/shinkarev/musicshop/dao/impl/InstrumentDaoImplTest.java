package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.dao.InstrumentDao;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.exception.DaoException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Optional;

public class InstrumentDaoImplTest {
    private InstrumentDao instrumentDao;
    private final long INSTRUMENT_ID = 19;
    private final String EXPECTED_INSTRUMENT_NAME = "Dunlop DEN1056";

    @BeforeTest
    public void before() {
        instrumentDao = new InstrumentDaoImpl();
    }

    @Test
    public void testFindEntityById() throws DaoException {
        Optional<Instrument> optionalInstrument = instrumentDao.findEntityById(INSTRUMENT_ID);
        Assert.assertEquals(optionalInstrument.orElseThrow().getName(), EXPECTED_INSTRUMENT_NAME);
    }

    @AfterTest
    public void after() {
        instrumentDao = null;
    }
}