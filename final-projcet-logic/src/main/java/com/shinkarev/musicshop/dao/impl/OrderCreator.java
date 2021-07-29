package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderCreator {
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private OrderCreator() {}
    static Order createOrder(ResultSet resultSet) throws DaoException {
        Order order = new Order();
        try {
            order.setId(resultSet.getLong(OrderFields.ORDER_ID));
            String date = resultSet.getString(OrderFields.ODER_DATE);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            order.setOrderDate(dateTime);
            order.setUserId(resultSet.getLong(OrderFields.USER_ID));
            order.setPrice(resultSet.getDouble(OrderFields.PRICE));
            order.setAddress(resultSet.getString(OrderFields.ADDRESS));
            order.setStatus(OderType.valueOf(resultSet.getString(OrderFields.ODER_STATUS)));
        } catch (SQLException ex) {
            throw new DaoException("Error. Impossible create instrument", ex);
        }
        return order;
    }
}
