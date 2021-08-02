package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.dao.OrderDao;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.pool.ConnectionPool;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.shinkarev.musicshop.dao.impl.SqlQuery.*;

public class OrderDaoImpl implements OrderDao {
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Optional<Order> findEntityById(Long id) throws DaoException {
        Order order = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_ORDER_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order = OrderCreator.createOrder(resultSet);
                order.setItems(getOrderItems(order.getId()));
            }
        } catch (SQLException ex) {
            throw new DaoException("Error of creating of order", ex);
        }
        return Optional.ofNullable(order);
    }

    @Override
    public List<Order> findAll() throws DaoException {
        throw new UnsupportedOperationException("Impossible get All orders");
    }

    @Override
    public boolean create(Order order) throws DaoException {
        boolean flag = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUserId());
            DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_PATTERN);
            statement.setString(2, order.getOrderDate().format(format));
            statement.setString(3, order.getAddress());
            statement.setInt(4, OderType.ordinal(order.getStatus()));
            statement.setDouble(5, order.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                order.setId(resultSet.getLong(1));
                long orderId = order.getId();
                if (saveItemsInOrder(order.getItems(), orderId)) {
                    flag = true;
                }
            }
        } catch (SQLException ex) {
            throw new DaoException("Error of creating order", ex);
        }
        return flag;
    }

    @Override
    public boolean update(Order order) throws DaoException {
        throw new UnsupportedOperationException("Impossible update order");
    }

    @Override
    public List<Order> findOrderByUserId(Long id) throws DaoException {
        Order order;
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_USER_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order = OrderCreator.createOrder(resultSet);
                order.setItems(getOrderItems(order.getId()));
                orders.add(order);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error of creating of order", ex);
        }
        return orders;
    }

    private boolean saveItemsInOrder(List<Instrument> instruments, long orderId) throws SQLException {
        int rowsUpdate = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_ITEMS_TO_ORDER)) {
            for (Instrument item : instruments) {
                statement.setLong(1, orderId);
                statement.setLong(2, item.getInstrument_id());
                if (statement.executeUpdate() == 1) {
                    rowsUpdate++;
                }
            }
        }
        return rowsUpdate == instruments.size();
    }

    private List<Instrument> getOrderItems(long orderId) throws DaoException {
        List<Instrument> instruments = new ArrayList<>();
        ResultSet resultSet;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_ORDER_ITEMS)) {
            statement.setLong(1, orderId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Instrument instrument = InstrumentCreator.createInstrument(resultSet);
                instruments.add(instrument);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        logger.info(instruments.size() > 0 ? "Were found " + instruments.size() + " instruments" : "Instruments not found");
        return instruments;
    }

    @Override
    public List<Order> findOrderByStatus(long userId, OderType status) throws DaoException {
        Order order;
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_STATUS)) {
            statement.setLong(1, userId);
            statement.setLong(2, OderType.ordinal(status));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order = OrderCreator.createOrder(resultSet);
                order.setItems(getOrderItems(order.getId()));
                orders.add(order);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error. Impossible get orders from DB.", ex);
        }
        return orders;
    }

    @Override
    public boolean changeOrderStatusById(long orderId, OderType status) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_ORDER_STATUS)) {
            statement.setInt(1, OderType.ordinal(status));
            statement.setLong(2, orderId);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Error. Impossible change data into DB.", ex);
        }
        return rowsUpdate == 1;
    }
}
