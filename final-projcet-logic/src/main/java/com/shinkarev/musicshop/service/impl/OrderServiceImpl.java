package com.shinkarev.musicshop.service.impl;

import com.shinkarev.musicshop.dao.OrderDao;
import com.shinkarev.musicshop.dao.impl.OrderDaoImpl;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private static Logger logger = LogManager.getLogger();
    private OrderDao orderDao = new OrderDaoImpl();
    private static OrderService instance;

    private OrderServiceImpl() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public int getOrderCount(OderType type) throws ServiceException {
        try {
            return orderDao.getOrderCount(type);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with instrument count .", e);
            throw new ServiceException("Error with instrument count .", e);
        }
    }

    @Override
    public List<Order> readByPage(int page) throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findByPage(page);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with find all Users .", e);
            throw new ServiceException("Error with find all Users .", e);
        }
        return orders;
    }

    @Override
    public int getOrderCount() throws ServiceException {
        try {
            return orderDao.getOrderCount();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error with instrument count .", e);
            throw new ServiceException("Error with instrument count .", e);
        }
    }

    @Override
    public List<Order> findOrderByStatus(OderType status, int page) throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findOrdersByStatus(status, page);
        } catch (DaoException ex) {
            throw new ServiceException("Fatal. Error of getting orders", ex);
        }
        return orders;
    }

    @Override
    public List<Order> findAllOrders() throws ServiceException {
        List<Order> orders= new ArrayList<>();
        try {
            orders = orderDao.findAll();
        } catch (DaoException ex) {
            throw new ServiceException("Fatal. Error of getting orders", ex);
        }
        return orders;
    }

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
    public List<Order> findUserOrderByStatus(long userId, OderType status) throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findUserOrderByStatus(userId, status);
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
        Order order = null;
        try {
            Optional<Order> optionalOrder = orderDao.findEntityById(orderId);
            if (optionalOrder.isPresent()) {
                order = optionalOrder.get();
            }
        } catch (DaoException ex) {
            throw new ServiceException("Fatal. Error of changing order status", ex);
        }
        return Optional.ofNullable(order);
    }
}
