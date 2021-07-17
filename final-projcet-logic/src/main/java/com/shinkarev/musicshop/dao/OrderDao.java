package com.shinkarev.musicshop.dao;

import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.DaoException;

import java.util.Date;
import java.util.List;

public interface OrderDao extends BaseDao<Long, Order> {
    List<Order> findOrderByUserId(Long id) throws DaoException;

    List<Order> findOrderByInstrumentId(Long id) throws DaoException;

    List<Order> findOrderByDate(Date date);
}
