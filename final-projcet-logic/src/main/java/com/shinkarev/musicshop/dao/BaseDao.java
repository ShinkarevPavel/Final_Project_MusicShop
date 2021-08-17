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

/**
 * The {@link BaseDao} interface
 * is a base interface for other DAO interfaces,
 * provides access to the database
 */

public interface BaseDao<K, T extends Entity> {
    Logger logger = LogManager.getLogger();
    int PAGE_SIZE = 3;

    /**
     * @param id sought entity id
     * @return Optional sought entity
     * @throws DaoException if the request to data base could not be handled
     */

    Optional<T> findEntityById(K id) throws DaoException;

    /**
     * @return collection of all entities
     * @throws DaoException if the request to data base could not be handled
     */

    List<T> findAll() throws DaoException;

    /**
     * @param t entity that will be added to data base
     * @return true if entity was added otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

    boolean create(T t) throws DaoException;

    /**
     *
     * @param t entity that will be updated into data base
     * @return true if entity was updated otherwise false
     * @throws DaoException if the request to data base could not be handled
     */

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

    /**
     * @param sourceQuery current query to data base
     * @return count of rows that data base contains for current query
     * @throws DaoException if the request to data base could not be handled.
     */

    default int rowCountByQuery(String sourceQuery) throws DaoException {
        int result = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM (" + sourceQuery + ") as tbl")
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
