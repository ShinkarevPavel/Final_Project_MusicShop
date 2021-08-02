package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.dao.InstrumentDao;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentStatusType;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.DaoException;

import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.shinkarev.musicshop.dao.impl.InstrumentField.*;

public class InstrumentCreator {
    private InstrumentCreator() {
    }

    static Instrument createInstrument(ResultSet resultSet) throws DaoException  {
        InstrumentDaoImpl instrumentDao = new InstrumentDaoImpl();
        Instrument instrument = new Instrument();
        try {
            instrument.setInstrument_id(resultSet.getLong(INSTRUMENT_ID));
            instrument.setName(resultSet.getString(NAME));
            instrument.setBrand(resultSet.getString(BRAND));
            instrument.setCountry(resultSet.getString(COUNTRY));
            instrument.setPrice(resultSet.getDouble(PRICE));
            double rating = instrumentDao.getInstrumentRating(resultSet.getLong(INSTRUMENT_ID));
            instrument.setRating(rating);
            instrument.setDescription(resultSet.getString(DESCRIPTION));
            instrument.setInstrumentStatus(InstrumentStatusType.valueOf(resultSet.getString(STATUS_ID)));
            instrument.setType(InstrumentType.valueOf(resultSet.getString(INSTRUMENT_TYPE)));

            List<Blob> images = instrumentDao.getInstrumentImages(instrument.getInstrument_id());
            List<String> convertedImages = instrumentDao.convertBlobToString(images);
            instrument.setImage(convertedImages);

        } catch (SQLException ex) {
            throw new DaoException("Error. Impossible create instrument", ex);
        } catch (IOException e) {
            throw new DaoException("Error of images converting", e);
        }
        return instrument;
    }
}
