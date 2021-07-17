package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.dao.OrderDao;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.shinkarev.musicshop.dao.impl.SqlQuery.SQL_FIND_ORDER_BY_ID;

public class OderDaoImpl implements OrderDao {
    @Override
    public Optional<Order> findEntityById(Long id) throws DaoException {
        Optional<Order> optionalOrder;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_ID)) {

        } catch (SQLException ex) {
            throw new DaoException("Error of creating of order", ex);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() throws DaoException {
        return null;
    }

    @Override
    public boolean create(Order order) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Order order) throws DaoException {
        return false;
    }

    @Override
    public List<Order> findOrderByUserId(Long id) throws DaoException {
        return null;
    }

    @Override
    public List<Order> findOrderByInstrumentId(Long id) throws DaoException {
        return null;
    }

    @Override
    public List<Order> findOrderByDate(Date date) {
        return null;
    }
}
