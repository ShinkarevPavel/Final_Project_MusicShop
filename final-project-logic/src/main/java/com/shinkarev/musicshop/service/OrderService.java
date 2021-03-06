package com.shinkarev.musicshop.service;

import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderService {

    List<Order> readByPage(int page) throws ServiceException;

    int getOrderCount(OderType type) throws ServiceException;

    int getOrderCount() throws ServiceException;

    List<Order> findAllOrders() throws ServiceException;

    boolean addOrder(Order order, Map<Long, Integer> items) throws ServiceException;

    List<Order> getOderByUserId(long userId) throws ServiceException;

    Optional<Order> getOderByOrderId(long orderId) throws ServiceException;

    List<Order> findUserOrderByStatus(long userId, OderType status) throws ServiceException;

    boolean changeOrderStatusByOrderId(long orderId, OderType status) throws ServiceException;

    Optional<Order> findOrderById(long orderId) throws ServiceException;

    List<Order> findOrderByStatus(OderType status, int page) throws ServiceException;
}