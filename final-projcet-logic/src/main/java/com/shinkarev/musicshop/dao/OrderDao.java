package com.shinkarev.musicshop.dao;

import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.DaoException;

import java.util.List;
import java.util.Map;

public interface OrderDao extends BaseDao<Long, Order> {
    List<Order> findOrdersByStatus(OderType status) throws DaoException;

    List<Order> findOrderByUserId(Long id) throws DaoException;

    List<Order> findUserOrderByStatus(long userId, OderType status) throws DaoException;

    boolean changeOrderStatusById(long orderId, OderType status) throws DaoException;

    boolean createOrder(Order order, Map<Long, Integer> items) throws DaoException;
}
