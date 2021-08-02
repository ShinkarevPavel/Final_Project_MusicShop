package com.shinkarev.musicshop.dao;

import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentStatusType;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.DaoException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;


public interface InstrumentDao extends BaseDao<Long, Instrument> {


    List<Instrument> findInstrumentByBrand(String brand) throws DaoException;

    List<Instrument> findInstrumentByStatus(InstrumentStatusType status) throws DaoException;

    List<Instrument> findInstrumentByType(InstrumentType type) throws DaoException;

    List<Instrument> findInstrumentByRating(int rating, InstrumentType type) throws DaoException;

    double getInstrumentRating(long instrumentId) throws DaoException;

    boolean isRated(long userId, long instrumentId) throws DaoException;

    boolean setInstrumentRating(long userId, long instrumentId, int rating) throws DaoException;

    boolean changeInstrumentStatusById(long instrumentId, InstrumentStatusType statusType) throws DaoException;

    boolean changeInstrumentTypeById(long instrumentId, InstrumentType type) throws DaoException;

    boolean addItemToBucket(long userId, long instrumentId) throws DaoException;

    boolean removeItemFromBucket(long userId, long instrumentId) throws DaoException;

    List<Instrument> findAddedToBucketItems(long userId) throws DaoException;

    boolean clearUserBucket(long userId) throws DaoException;

    boolean addImageToInstrumentById(long instrumentId, InputStream inputStream) throws DaoException;

    boolean addInstrument(Instrument instrument, List<InputStream> images) throws DaoException;

}
