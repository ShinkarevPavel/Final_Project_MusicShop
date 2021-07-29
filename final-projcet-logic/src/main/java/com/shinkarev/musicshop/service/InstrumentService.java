package com.shinkarev.musicshop.service;

import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentStatusType;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface InstrumentService {

    boolean addInstrument(Instrument instrument) throws ServiceException;
    List<Instrument> findInstrumentByType(InstrumentType instrumentType) throws ServiceException;
    Optional<Instrument> findInstrumentById(long instrumentId) throws ServiceException;
    boolean instrumentStatusControl(long instrumentId, InstrumentStatusType statusType) throws ServiceException;
    boolean instrumentTypeControl(long instrumentId, InstrumentType type) throws ServiceException;
    boolean addItemToBucket(long userId, long instrumentId) throws ServiceException;
    boolean removeItemFromBucket(long userId, long instrumentId) throws ServiceException;
    List<Instrument> getUserBucket(long userId) throws ServiceException;
    boolean clearUserBucket(long userId) throws ServiceException;
    List<Instrument> getAllEntity() throws ServiceException;
}
