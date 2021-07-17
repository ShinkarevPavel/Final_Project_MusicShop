package com.shinkarev.musicshop.dao;

import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentStatusType;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.DaoException;

import java.util.List;


public interface InstrumentDao extends BaseDao<Long, Instrument> {
    List<Instrument> findInstrumentByBrand(String brand) throws DaoException;

    List<Instrument> findInstrumentBuStatus(InstrumentStatusType status) throws DaoException;

    List<Instrument> findInstrumentByType(InstrumentType type) throws DaoException;

    List<Instrument> findInstrumentByRating(int rating, InstrumentType type) throws DaoException;

    double getInstrumentRating(Instrument instrument) throws DaoException;
}
