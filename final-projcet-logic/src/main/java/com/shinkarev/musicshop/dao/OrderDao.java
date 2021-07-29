package com.shinkarev.musicshop.dao;

import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.DaoException;

import java.util.List;

public interface OrderDao extends BaseDao<Long, Order> {
    List<Order> findOrderByUserId(Long id) throws DaoException;
    List<Order> findOrderByStatus(long userId, OderType status) throws DaoException;
    boolean changeOrderStatusById(long orderId, OderType status) throws DaoException;
}
