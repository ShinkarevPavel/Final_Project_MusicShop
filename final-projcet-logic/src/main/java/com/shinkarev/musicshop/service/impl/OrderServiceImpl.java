package com.shinkarev.musicshop.service.impl;

import com.shinkarev.musicshop.dao.impl.OderDaoImpl;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.OrderService;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private OderDaoImpl orderDao = new OderDaoImpl();

    @Override
    public boolean addOrder(Order order) throws ServiceException {
        boolean isAdded = false;
        try {
            if (orderDao.create(order)) {
                isAdded = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Fatal. Error of adding order", ex);
        }
        return isAdded;
    }

    @Override
    public List<Order> getOderByUserId(long userId) throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findOrderByUserId(userId);
        } catch (DaoException ex) {
            throw new ServiceException("Fatal. Error of getting user orders", ex);
        }
        return orders;
    }

    @Override
    public Optional<Order> getOderByOrderId(long orderId) throws ServiceException {
        Order order = null;
        try {
            orderDao.findEntityById(orderId);
        } catch (DaoException ex) {
            throw new ServiceException("Fatal. Error of getting order", ex);
        }
        return Optional.ofNullable(order);
    }
}