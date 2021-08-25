package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderCreator {
    private static Logger logger = LogManager.getLogger();
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private OrderCreator() {}
    static Order createOrder(ResultSet resultSet) throws DaoException {
        Order order = new Order();
        try {
            order.setId(resultSet.getLong(OrderField.ORDER_ID));
            String date = resultSet.getString(OrderField.ODER_DATE);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            order.setOrderDate(dateTime);
            order.setUserId(resultSet.getLong(OrderField.USER_ID));
            order.setPrice(resultSet.getDouble(OrderField.PRICE));
            order.setAddress(resultSet.getString(OrderField.ADDRESS));
            order.setStatus(OderType.valueOf(resultSet.getString(OrderField.ODER_STATUS)));
            order.setPayment(resultSet.getString(OrderField.PAYMENT));
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of order creating", ex);
            throw new DaoException("Error. Impossible create instrument", ex);
        }
        return order;
    }
}
