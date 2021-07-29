package com.shinkarev.musicshop.service.impl;

import com.shinkarev.musicshop.dao.impl.InstrumentDaoImpl;
import com.shinkarev.musicshop.dao.impl.UserDaoImpl;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentStatusType;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InstrumentServiceImpl implements InstrumentService {
    private Logger logger = LogManager.getLogger();
    private InstrumentDaoImpl instrumentDao = new InstrumentDaoImpl();

    @Override
    public Optional<Instrument> findInstrumentById(long instrumentId) throws ServiceException {
//Todo
        return Optional.empty();
    }


    @Override
    public List<Instrument> getAllEntity() throws ServiceException {
        InstrumentDaoImpl instrumentDao = new InstrumentDaoImpl();
        List<Instrument> instruments;
        try {
            instruments = instrumentDao.findAll();
        } catch (DaoException ex) {
            throw new ServiceException("Error. Impossible get data from Dao", ex);
        }
        return instruments;
    }

    @Override
    public boolean addInstrument(Instrument instrument) throws ServiceException{
        boolean result = false;
        try {
            if (instrumentDao.create(instrument)) {
                result = true;
            }
        } catch (DaoException ex) {
           throw new ServiceException("Error. Impossible create instrument", ex);
        }
        return result;
    }

    @Override
    public List<Instrument> findInstrumentByType(InstrumentType instrumentType) throws ServiceException {
        List<Instrument> instruments;
        InstrumentDaoImpl instrumentDao = new InstrumentDaoImpl();
        try {
            instruments = instrumentDao.findInstrumentByType(instrumentType);
        } catch (DaoException ex) {
            throw new ServiceException("Error. Impossible get instrument(s) from DB", ex);
        }
        return instruments;
    }

    @Override
    public boolean instrumentStatusControl(long instrumentId, InstrumentStatusType statusType) throws ServiceException {
        boolean isChanged = false;
        InstrumentDaoImpl instrumentDao = new InstrumentDaoImpl();
        try {
            Optional<Instrument> optionalInstrument = instrumentDao.findEntityById(instrumentId);
            if (optionalInstrument.isPresent()) {
                instrumentDao.changeInstrumentStatusById(instrumentId, statusType);
                isChanged = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Impossible change status for user", ex);
        }
        return isChanged;
    }

    @Override
    public boolean instrumentTypeControl(long instrumentId, InstrumentType type) throws ServiceException {
        boolean isChanged = false;
        InstrumentDaoImpl instrumentDao = new InstrumentDaoImpl();
        try {
            Optional<Instrument> optionalInstrument = instrumentDao.findEntityById(instrumentId);
            if (optionalInstrument.isPresent()) {
                instrumentDao.changeInstrumentTypeById(instrumentId, type);
                isChanged = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Impossible change status for user", ex);
        }
        return isChanged;
    }

    @Override
    public boolean addItemToBucket(long userId, long instrumentId) throws ServiceException {
        boolean isAdded = false;
        InstrumentDaoImpl instrumentDao = new InstrumentDaoImpl();
        try {
            if (instrumentDao.addItemToBucket(userId, instrumentId)) {
                isAdded = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Error. Item wasn't added to DB", ex);
        }

        return isAdded;
    }

    @Override
    public boolean removeItemFromBucket(long userId, long instrumentId) throws ServiceException {
        boolean isAdded = false;
        InstrumentDaoImpl instrumentDao = new InstrumentDaoImpl();
        try {
            if (instrumentDao.removeItemFromBucket(userId, instrumentId)) {
                isAdded = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Error. Item wasn't added to DB", ex);
        }

        return isAdded;
    }

    @Override
    public List<Instrument> getUserBucket(long userId) throws ServiceException {
        List<Instrument> instruments;
        InstrumentDaoImpl instrumentDao = new InstrumentDaoImpl();
        try {
            instruments = instrumentDao.findAddedToBucketItems(userId);
        } catch (DaoException ex) {
            throw new ServiceException("Fatal. Error of getting items", ex);
        }
        return instruments;
    }

    @Override
    public boolean clearUserBucket(long userId) throws ServiceException {
        boolean isAdded = false;
        InstrumentDaoImpl instrumentDao = new InstrumentDaoImpl();
        try {
            if (instrumentDao.clearUserBucket(userId)) {
                isAdded = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Error. Item wasn't clear user bucket in DB", ex);
        }

        return isAdded;
    }
}
