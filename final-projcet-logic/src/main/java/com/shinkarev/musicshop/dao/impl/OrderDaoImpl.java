package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.dao.OrderDao;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.shinkarev.musicshop.dao.impl.InstrumentField.INSTRUMENT_QUANTITY;
import static com.shinkarev.musicshop.dao.impl.SqlQuery.*;

public class OrderDaoImpl implements OrderDao {
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static Logger logger = LogManager.getLogger();

    @Override
    public int getOrderCount() throws DaoException {
        return rowCountByQuery(SQL_FIND_ALL_ORDERS);
    }
    @Override
    public int getOrderCount(OderType type) throws DaoException {
        return orderRowCountByQuery(type);
    }

    @Override
    public List<Order> findByPage(int page) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(buildPageableQuery(SQL_FIND_ALL_ORDERS + ORDER_ORDER_BY, page))) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = OrderCreator.createOrder(resultSet);
                orders.add(order);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error with find all Users.", ex);
            throw new DaoException("Error with find all Users .", ex);
        }
        return orders;

    }

    public List<Order> findOrderByType(OderType type, int page) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(buildPageableQuery(SQL_FIND_ORDER_BY_STATUS + INSTRUMENT_ORDER_BY, page) )) {
            statement.setInt(1, OderType.ordinal(type));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = OrderCreator.createOrder(resultSet);
                orders.add(order);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error with find orders.", ex);
            throw new DaoException("Error of finding instruments", ex);
        }
        return orders;
    }

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
            logger.log(Level.ERROR, "Error with find order.", ex);
            throw new DaoException("Error of creating of order", ex);
        }
        return Optional.ofNullable(order);
    }

    @Override
    public List<Order> findAll() throws DaoException {
        Order order;
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ORDERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order = OrderCreator.createOrder(resultSet);
                order.setItems(getOrderItems(order.getId()));
                orders.add(order);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error with finding all.", ex);
            throw new DaoException("Error of creating of order", ex);
        }
        return orders;

    }

    @Override
    public boolean create(Order order) throws DaoException {
        throw new UnsupportedOperationException("This method not supported.");
    }

    @Override
    public boolean createOrder(Order order, Map<Long, Integer> items) throws DaoException {
        boolean flag = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUserId());
            DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_PATTERN);
            statement.setString(2, order.getOrderDate().format(format));
            statement.setString(3, order.getAddress());
            statement.setInt(4, OderType.ordinal(order.getStatus()));
            statement.setDouble(5, order.getPrice());
            statement.setString(6, order.getPayment());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
            if (saveItemsInOrder(items, order.getId())) {
                flag = true;
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error with find order.", ex);
            throw new DaoException("Error of creating order", ex);
        }
        return flag;
    }

    @Override
    public boolean update(Order order) throws DaoException {
        throw new UnsupportedOperationException("This method not supported.");
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
            logger.log(Level.ERROR, "Error of finding of order", ex);
            throw new DaoException("Error of finding of order", ex);
        }
        return orders;
    }

    private boolean saveItemsInOrder(Map<Long, Integer> instruments, long orderId) throws SQLException {
        int rowsUpdate = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_ITEMS_TO_ORDER)) {
            for (Map.Entry<Long, Integer> item : instruments.entrySet()) {
                statement.setLong(1, orderId);
                statement.setLong(2, item.getKey());
                statement.setInt(3, item.getValue());
                if (statement.executeUpdate() == 1) {
                    rowsUpdate++;
                }
            }
        }
        return rowsUpdate == instruments.size();
    }

    private Map<Instrument, Integer> getOrderItems(long orderId) throws DaoException {
        Map<Instrument, Integer> instruments = new HashMap<>();
        ResultSet resultSet;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_ORDER_ITEMS)) {
            statement.setLong(1, orderId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Instrument instrument = InstrumentCreator.createInstrument(resultSet);
                int quantity = resultSet.getInt(INSTRUMENT_QUANTITY);
                instruments.put(instrument, quantity);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        logger.info(instruments.size() > 0 ? "Were found " + instruments.size() + " instruments" : "Instruments not found");
        return instruments;
    }

    @Override
    public List<Order> findUserOrderByStatus(long userId, OderType status) throws DaoException {
        Order order;
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_ORDER_BY_STATUS)) {
            statement.setLong(1, userId);
            statement.setLong(2, OderType.ordinal(status));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order = OrderCreator.createOrder(resultSet);
                order.setItems(getOrderItems(order.getId()));
                orders.add(order);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error. Impossible get orders from DB.", ex);
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
            logger.log(Level.ERROR, "Error. Impossible change data into DB.", ex);
            throw new DaoException("Error. Impossible change data into DB.", ex);
        }
        return rowsUpdate == 1;
    }

    @Override
    public List<Order> findOrdersByStatus(OderType status, int page) throws DaoException {
        Order order;
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(buildPageableQuery(SQL_FIND_ORDER_BY_STATUS + ORDER_ORDER_BY, page))) {
            statement.setLong(1, OderType.ordinal(status));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order = OrderCreator.createOrder(resultSet);
                order.setItems(getOrderItems(order.getId()));
                orders.add(order);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error. Impossible get orders from DB.", ex);
            throw new DaoException("Error. Impossible get orders from DB.", ex);
        }
        return orders;
    }

    private int orderRowCountByQuery(OderType type) throws DaoException {
        int result = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_ROW_COUNT_BY_TYPE)
        ) {
            statement.setInt(1, OderType.ordinal(type));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Can't count row count. ", ex);
            throw new DaoException("Can't count row count.", ex);
        }
        return result;
    }
}
