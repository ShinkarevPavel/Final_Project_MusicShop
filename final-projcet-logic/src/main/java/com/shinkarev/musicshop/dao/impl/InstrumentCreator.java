package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentStatusType;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.shinkarev.musicshop.dao.impl.InstrumentField.*;

public class InstrumentCreator {
    private InstrumentCreator() {
    }

    static Instrument createInstrument(ResultSet resultSet) throws DaoException {
        Instrument instrument = new Instrument();
        try {
            instrument.setInstrument_id(resultSet.getLong(INSTRUMENT_ID));
            instrument.setName(resultSet.getString(NAME));
            instrument.setBrand(resultSet.getString(BRAND));
            instrument.setCountry(resultSet.getString(COUNTRY));
            instrument.setPrice(resultSet.getDouble(PRICE));
            instrument.setRating(resultSet.getDouble(RATING));
            instrument.setDescription(resultSet.getString(DESCRIPTION));
            instrument.setInstrumentStatus(InstrumentStatusType.valueOf(resultSet.getString(STATUS_ID)));
            instrument.setType(InstrumentType.valueOf(resultSet.getString(INSTRUMENT_TYPE)));
        } catch (SQLException ex) {
            throw new DaoException("Error. Impossible create instrument", ex);
        }
        return instrument;
    }
}
