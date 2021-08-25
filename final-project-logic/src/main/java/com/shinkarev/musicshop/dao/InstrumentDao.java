package com.shinkarev.musicshop.dao;

import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentStatusType;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.DaoException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface InstrumentDao extends BaseDao<Long, Instrument> {

    /**
     * @param type {@link InstrumentType} according that will be counted rows
     * @return count of rows
     * @throws DaoException if the request to data base could not be handled
     */

    int getInstrumentCount(InstrumentType type) throws DaoException;

    /**
     * @return all counted rows
     * @throws DaoException if the request to data base could not be handled
     */

    int getInstrumentCount() throws DaoException;

    /**
     * @param page current page
     * @return collection of {@link Instrument}s
     * @throws DaoException if the request to data base could not be handled
     */

    List<Instrument> findByPage(int page) throws DaoException;

    /**
     * @param brand parameter by which the search will be performed
     * @return collection of respective {@link Instrument}s
     * @throws DaoException if the request to data base could not be handled
     */

    List<Instrument> findInstrumentByBrand(String brand) throws DaoException;

    /**
     * @param status parameter by which the search will be performed
     * @return collection of respective {@link Instrument}s
     * @throws DaoException if the request to data base could not be handled
     */

    List<Instrument> findInstrumentByStatus(InstrumentStatusType status) throws DaoException;

    /**
     * @param type parameter by which the search will be performed
     * @param page quantity {@link Instrument}s on the current page
     * @return collection of respective {@link Instrument}s
     * @throws DaoException if the request to data base could not be handled
     */

    List<Instrument> findInstrumentByType(InstrumentType type, int page) throws DaoException;

    List<Instrument> findInstrumentByRating(int rating, InstrumentType type) throws DaoException;

    /**
     * @param instrumentId parameter by which the search will be performed for current {@link Instrument}
     * @return {@link Instrument} rating
     * @throws DaoException if the request to data base could not be handled
     */
    double getInstrumentRating(long instrumentId) throws DaoException;

    /**
     * @param userId       parameter by which the search will be performed for current {@link Instrument} rating
     * @param instrumentId {@link Instrument}s id for searching into data base
     * @return true if {@link Instrument} was rated by current {@link User} otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean isRated(long userId, long instrumentId) throws DaoException;

    /**
     * @param userId       {@link User} id
     * @param instrumentId {@link Instrument} id
     * @param rating       that current {@link User} set for current {@link Instrument}
     * @return true if rating for {@link Instrument} was set by current {@link User} otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean setInstrumentRating(long userId, long instrumentId, int rating) throws DaoException;

    /**
     * @param instrumentId current {@link Instrument} id
     * @param statusType   status of current {@link Instrument}
     * @return true if status for {@link Instrument} was changed otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean changeInstrumentStatusById(long instrumentId, InstrumentStatusType statusType) throws DaoException;

    /**
     * @param instrumentId current {@link Instrument} id
     * @param type         type of current {@link Instrument}
     * @return true if type for {@link Instrument} was changed otherwise false
     * @throws DaoException if the request to data base could not be handled
     */
    boolean changeInstrumentTypeById(long instrumentId, InstrumentType type) throws DaoException;

    /**
     * @param userId       current {@link User} id
     * @param instrumentId {@link Instrument} id that adding to {@link User} cart
     * @return true if {@link Instrument} was added otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean addItemToCart(long userId, long instrumentId) throws DaoException;

    /**
     * @param userId       current {@link User} id
     * @param instrumentId {@link Instrument} id that adding to {@link User} cart
     * @return true if {@link Instrument} was removed from cart otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean removeItemFromCart(long userId, long instrumentId) throws DaoException;

    /**
     * @param userId parameter by which the search will be performed
     * @return Map of {@link Instrument}s and quantity for each item
     * @throws DaoException if the request to data base could not be handled
     */

    Map<Instrument, Integer> findAddedToCartItems(long userId) throws DaoException;

    /**
     * @param userId parameter by which the cart will be clearing
     * @return true if cart was cleared otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean clearUserCart(long userId) throws DaoException;

    /**
     * @param instrumentId parameter for which {@link Instrument} adding images
     * @param inputStream  converted images
     * @return true if images was added, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean addImageToInstrumentById(long instrumentId, InputStream inputStream) throws DaoException;

    /**
     * @param instrument {@link Instrument} that will be added to data base
     * @param images     collection images that describe current {@link Instrument}
     * @return true if {@link Instrument} was added to data base, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean addInstrument(Instrument instrument, List<InputStream> images) throws DaoException;

    /**
     * @param userId       id current {@link User}
     * @param instrumentId id current {@link Instrument}
     * @return true if {@link Instrument} in the {@link User} cart otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean isInCart(long userId, long instrumentId) throws DaoException;

    /**
     * @param userId       current {@link User}
     * @param instrumentId id current {@link Instrument}
     * @param quantity     {@link Instrument} quantity
     * @return true if {@link Instrument} quantity was set,otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean setInstrumentQuantity(long userId, long instrumentId, int quantity) throws DaoException;

}
