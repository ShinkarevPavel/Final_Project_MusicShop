package com.shinkarev.musicshop.service.impl;

import com.shinkarev.musicshop.dao.InstrumentDao;
import com.shinkarev.musicshop.dao.impl.InstrumentDaoImpl;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentStatusType;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InstrumentServiceImpl implements InstrumentService {
    private Logger logger = LogManager.getLogger();
    private InstrumentDao instrumentDao = new InstrumentDaoImpl();

    @Override
    public int getInstrumentCount() throws ServiceException {
        try {
            return instrumentDao.getInstrumentCount();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with instrument count .", e);
            throw new ServiceException("Error with instrument count .", e);
        }
    }



    @Override
    public List<Instrument> readByPage(int page) throws ServiceException {
        List<Instrument> instruments;
        try {
            instruments = instrumentDao.findByPage(page);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find all Users .", e);
            throw new ServiceException("Error with find all Users .", e);
        }
        return instruments;
    }

    @Override
    public Optional<Instrument> findInstrumentById(long instrumentId) throws ServiceException {
        Instrument instrument = null;
        try {
            Optional<Instrument> optionalInstrument = instrumentDao.findEntityById(instrumentId);
            if (optionalInstrument.isPresent()) {
                instrument = optionalInstrument.get();
            }
        } catch (DaoException ex) {
            throw new ServiceException("Error. Impossible get data from Dao", ex);
        }

        return Optional.ofNullable(instrument);
    }

    @Override
    public List<Instrument> getAllEntity() throws ServiceException {
        List<Instrument> instruments;
        try {
            instruments = instrumentDao.findAll();
        } catch (DaoException ex) {
            throw new ServiceException("Error. Impossible get data from Dao", ex);
        }
        return instruments;
    }

    @Override
    public boolean addInstrument(Instrument instrument, List<InputStream> images) throws ServiceException{
        boolean result;
        try {
            result= instrumentDao.addInstrument(instrument, images);
        } catch (DaoException ex) {
           throw new ServiceException("Error. Impossible create instrument", ex);
        }
        return result;
    }

    @Override
    public List<Instrument> findInstrumentByType(InstrumentType instrumentType) throws ServiceException {
        List<Instrument> instruments;
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
        boolean isAdded;
        try {
            isAdded = instrumentDao.addItemToCart(userId, instrumentId);
        } catch (DaoException ex) {
            throw new ServiceException("Error. Item wasn't added to DB", ex);
        }

        return isAdded;
    }

    @Override
    public boolean removeItemFromBucket(long userId, long instrumentId) throws ServiceException {
        boolean isRemoved;
        try {
            isRemoved = instrumentDao.removeItemFromBucket(userId, instrumentId);
        } catch (DaoException ex) {
            throw new ServiceException("Error. Item wasn't added to DB", ex);
        }
        return isRemoved;
    }

    @Override
    public Map<Instrument, Integer> getUserBucket(long userId) throws ServiceException {
        Map<Instrument, Integer> instruments;
        try {
            instruments = instrumentDao.findAddedToCartItems(userId);
        } catch (DaoException ex) {
            throw new ServiceException("Fatal. Error of getting items", ex);
        }
        return instruments;
    }

    @Override
    public boolean clearUserBucket(long userId) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = instrumentDao.clearUserBucket(userId);
        } catch (DaoException ex) {
            throw new ServiceException("Error. Item wasn't clear user bucket in DB", ex);
        }
        return isAdded;
    }

    @Override
    public boolean saveInstrumentImage(long instrumentId, InputStream inputStream) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = instrumentDao.addImageToInstrumentById(instrumentId, inputStream);
        } catch (DaoException ex) {
            throw new ServiceException("Fatal. Error of adding image to DB", ex);
        }
        return isAdded;
    }

    @Override
    public boolean isRated(long userId, long instrumentId) throws ServiceException {
        boolean result;
        try {
            result = instrumentDao.isRated(userId, instrumentId);
        } catch (DaoException ex) {
            throw new ServiceException("Error of getting request", ex);
        }
        return result;
    }

    @Override
    public boolean isInBucket(long userId, long instrumentId) throws ServiceException {
        boolean result;
        try {
            result = instrumentDao.isInBucket(userId, instrumentId);
        } catch (DaoException ex) {
            throw new ServiceException("Error of getting request", ex);
        }
        return result;
    }

    @Override
    public boolean addInstrumentRating(long userId, long instrumentId, int rating) throws ServiceException {
        boolean result;
        try {
            result = instrumentDao.setInstrumentRating(userId, instrumentId, rating);
        } catch (DaoException ex) {
            throw new ServiceException("Error of getting request", ex);
        }
        return result;
    }

    @Override
    public boolean setInstrumentQuantity(long userId, long instrumentId, int quantity) throws ServiceException {
        boolean result;
        try {
            result = instrumentDao.setInstrumentQuantity(userId, instrumentId, quantity);
        } catch (DaoException ex) {
            throw new ServiceException("Error of getting request", ex);
        }
        return result;
    }
}
