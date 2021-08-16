package com.shinkarev.musicshop.dao;

import com.shinkarev.musicshop.entity.Entity;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public interface BaseDao<K, T extends Entity> {
    Logger logger = LogManager.getLogger();
    int PAGE_SIZE = 3;

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

    default String buildPageableQuery(String mainQuery, int pageNumber) {
        int limit = PAGE_SIZE;
        int offset = (limit * pageNumber) - limit;
        StringBuilder queryBuilder = new StringBuilder(mainQuery);
        queryBuilder.append(" LIMIT ");
        if (offset > 0) {
            queryBuilder.append(offset).append(", ");
        }
        queryBuilder.append(limit);
        return queryBuilder.toString();
    }

    default int rowCountByQuery(String sourceQuery) throws DaoException {
        int result = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM (" + sourceQuery + ") as tbl" )
        ) {
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
