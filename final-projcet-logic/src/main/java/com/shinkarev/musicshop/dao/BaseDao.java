package com.shinkarev.musicshop.dao;

import com.shinkarev.musicshop.entity.Entity;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface BaseDao<K, T extends Entity> {
    Logger logger = LogManager.getLogger();

    Optional<T> findEntityById(K id) throws DaoException;

    List<T> findAll() throws DaoException;

    boolean create(T t) throws DaoException;

    boolean update(T t) throws DaoException;

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            logger.error("Closing statement error ", ex);
        }
    }

    default void close(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
