package com.shinkarev.musicshop.dao;

import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentStatusType;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.DaoException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface InstrumentDao extends BaseDao<Long, Instrument> {

    int getInstrumentCount() throws DaoException;

    List<Instrument> findByPage(int page) throws DaoException;

    List<Instrument> findInstrumentByBrand(String brand) throws DaoException;

    List<Instrument> findInstrumentByStatus(InstrumentStatusType status) throws DaoException;

    List<Instrument> findInstrumentByType(InstrumentType type, int page) throws DaoException;

    List<Instrument> findInstrumentByRating(int rating, InstrumentType type) throws DaoException;

    double getInstrumentRating(long instrumentId) throws DaoException;

    boolean isRated(long userId, long instrumentId) throws DaoException;

    boolean setInstrumentRating(long userId, long instrumentId, int rating) throws DaoException;

    boolean changeInstrumentStatusById(long instrumentId, InstrumentStatusType statusType) throws DaoException;

    boolean changeInstrumentTypeById(long instrumentId, InstrumentType type) throws DaoException;

    boolean addItemToCart(long userId, long instrumentId) throws DaoException;

    boolean removeItemFromBucket(long userId, long instrumentId) throws DaoException;

    Map<Instrument, Integer> findAddedToCartItems(long userId) throws DaoException;

    boolean clearUserBucket(long userId) throws DaoException;

    boolean addImageToInstrumentById(long instrumentId, InputStream inputStream) throws DaoException;

    boolean addInstrument(Instrument instrument, List<InputStream> images) throws DaoException;

    boolean isInBucket(long userId, long instrumentId) throws DaoException;

    boolean setInstrumentQuantity(long userId, long instrumentId, int quantity) throws DaoException;

}
