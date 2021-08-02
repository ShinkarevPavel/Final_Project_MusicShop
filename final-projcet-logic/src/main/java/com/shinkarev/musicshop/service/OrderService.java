package com.shinkarev.musicshop.service;

import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    boolean addOrder(Order order) throws ServiceException;

    List<Order> getOderByUserId(long userId) throws ServiceException;

    Optional<Order> getOderByOrderId(long orderId) throws ServiceException;

    List<Order> findOrderByStatus(long userId, OderType status) throws ServiceException;

    boolean changeOrderStatusByOrderId(long orderId, OderType status) throws ServiceException;
}