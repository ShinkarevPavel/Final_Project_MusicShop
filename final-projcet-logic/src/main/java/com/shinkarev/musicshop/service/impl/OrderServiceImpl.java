package com.shinkarev.musicshop.service.impl;

import com.shinkarev.musicshop.dao.OrderDao;
import com.shinkarev.musicshop.dao.impl.OrderDaoImpl;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.OrderService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public boolean addOrder(Order order, Map<Long, Integer> items) throws ServiceException {
        boolean isAdded = false;
        try {
            if (orderDao.createOrder(order, items)) {
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

    @Override
    public List<Order> findOrderByStatus(long userId, OderType status) throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findOrderByStatus(userId, status);
        } catch (DaoException ex) {
            throw new ServiceException("Fatal. Error of getting order", ex);
        }
        return orders;
    }

    @Override
    public boolean changeOrderStatusByOrderId(long orderId, OderType status) throws ServiceException {
        boolean isChanged;
        try {
            isChanged = orderDao.changeOrderStatusById(orderId, status);
        } catch (DaoException ex) {
            throw new ServiceException("Fatal. Error of changing order status", ex);
        }
        return isChanged;
    }

    @Override
    public Optional<Order> findOrderById(long orderId) throws ServiceException {

        return Optional.empty();
    }
}
