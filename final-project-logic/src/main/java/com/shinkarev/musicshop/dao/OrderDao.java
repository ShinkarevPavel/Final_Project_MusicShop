package com.shinkarev.musicshop.dao;

import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.exception.DaoException;

import java.util.List;
import java.util.Map;

public interface OrderDao extends BaseDao<Long, Order> {

    /**
     * @param type {@link Order}s type
     * @return quantity of {@link Order}s certain type
     * @throws DaoException if the request to data base could not be handled
     */

    int getOrderCount(OderType type) throws DaoException;

    /**
     * @return count of all {@link Order}s
     * @throws DaoException if the request to data base could not be handled
     */

    int getOrderCount() throws DaoException;

    /**
     * @param page current page
     * @return collection of {@link Order}s on the page
     * @throws DaoException if the request to data base could not be handled
     */

    List<Order> findByPage(int page) throws DaoException;

    /**
     * @param status {@link Order} status which used for searching
     * @param page   current page
     * @return collection of {@link Order}s on the page
     * @throws DaoException if the request to data base could not be handled
     */

    List<Order> findOrdersByStatus(OderType status, int page) throws DaoException;

    /**
     * @param id {@link Order}s status
     * @return collection of {@link Order}s
     * @throws DaoException if the request to data base could not be handled
     */

    List<Order> findOrderByUserId(Long id) throws DaoException;

    /**
     * @param userId {@link User}s  id
     * @param status {@link User}s status
     * @return collection of {@link User} {@link Order}s
     * @throws DaoException if the request to data base could not be handled
     */

    List<Order> findUserOrderByStatus(long userId, OderType status) throws DaoException;

    /**
     * @param orderId {@link Order}s id
     * @param status  {@link Order}s status
     * @return true if status was changed, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean changeOrderStatusById(long orderId, OderType status) throws DaoException;

    /**
     * @param order current {@link Order}
     * @param items Map of orders items where K is {@link Instrument} id
     *              and V is {@link Instrument} quantity
     * @return true if {@link Order} was created, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean createOrder(Order order, Map<Long, Integer> items) throws DaoException;
}
