package com.shinkarev.musicshop.dao.impl;

class SqlQuery {


    private SqlQuery() {
    }

    /**
     * Requests to 'users' table into musician instruments database
     */

    static final String SQL_GET_ALL_USERS = "SELECT user_id, login, email, nickname, name, surename," +
            " status_value, role_value FROM users LEFT JOIN statuses ON users.status_id=statuses.id" +
            " LEFT JOIN roles ON users.role_id=roles.id";
    static final String SQL_FIND_USER_BY_ID = "SELECT user_id, login, email, nickname, name, surename," +
            " status_value, role_value FROM users" +
            " LEFT JOIN statuses ON users.status_id=statuses.id LEFT JOIN roles ON users.role_id=roles.id" +
            " WHERE user_id=?";
    static final String SQL_FIND_USER_BY_REGISTRATION_KEY = "SELECT user_id, login, email, nickname, name, surename," +
            " status_value, role_value FROM users" +
            " LEFT JOIN statuses ON users.status_id=statuses.id LEFT JOIN roles ON users.role_id=roles.id" +
            " WHERE registration_key =?";
    static final String SQL_CONTROL_USER_STATUS = "UPDATE users SET status_id=? WHERE user_id=?";
    static final String SQL_CONTROL_USER_ROLE = "UPDATE users SET role_id=? WHERE user_id=?";
    static final String SQL_ADD_USER = "INSERT INTO users(login, password, email, nickname, name, surename," +
            "  status_id, role_id, registration_key) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    static final String SQL_UPDATE_USER = "UPDATE users SET login=?, email=?, nickname=?, name=?, surename=?, status_id=?, role_id=?  WHERE user_id=?";
    static final String SQL_FIND_BY_LOGIN_AND_PASSWORD = "SELECT user_id, login, email, nickname, name, surename," +
            " status_value, role_value FROM users" +
            " LEFT JOIN statuses ON users.status_id=statuses.id LEFT JOIN roles ON users.role_id=roles.id WHERE login=? AND password=?";
    static final String SQL_FIND_USER_BY_ROLE = "SELECT user_id, login, email, nickname, name, surename," +
            "status_value, role_value FROM users LEFT JOIN roles ON users.role_id=roles.id LEFT JOIN statuses ON status_id=statuses.id WHERE role_id=?";
    static final String SQL_FIND_USER_BY_STATUS = "SELECT user_id, login, email, nickname, name, surename," +
            "status_value, role_value FROM users LEFT JOIN roles ON users.role_id=roles.id LEFT JOIN statuses ON status_id=statuses.id WHERE status_id=?";
    static final String SQL_GET_USER_PASSWORD = "SELECT password FROM users WHERE login=?";
    static final String SQL_FIND_USER_BY_LOGIN = "SELECT user_id, login, email, nickname, name, surename," +
            "status_value, role_value FROM users LEFT JOIN roles ON users.role_id=roles.id LEFT JOIN statuses ON status_id=statuses.id  WHERE login=?";
    static final String SQL_FIND_USER_BY_EMAIL = "SELECT user_id, login, email, nickname, name, surename," +
            "status_value, role_value FROM users LEFT JOIN roles ON users.role_id=roles.id LEFT JOIN statuses ON status_id=statuses.id  WHERE email=?";
    static final String SQL_FIND_USER_BY_NICKNAME = "SELECT user_id, login, email, nickname, name, surename," +
            "status_value, role_value FROM users LEFT JOIN roles ON users.role_id=roles.id LEFT JOIN statuses ON status_id=statuses.id  WHERE nickname=?";
    static final String USER_ORDER_BY = " ORDER BY login ";
    static final String SQL_USER_CHANGE_PASSWORD = "UPDATE users SET password=? WHERE user_id=?";

    /**
     * Requests to 'instruments' table into musician instruments database
     */
    static final String SQL_GET_ALL_INSTRUMENTS = "SELECT instrument_id, name, brand, country, price, description, rating, status, type FROM instruments" +
            " LEFT JOIN instruments_statuses ON instruments.status_id=id LEFT JOIN instruments_types ON instruments.instrument_type=type_id";
    static final String SQL_FIND_INSTRUMENT_BY_ID = "SELECT instrument_id, name, brand, country, price, description, rating, status, type" +
            " FROM instruments" +
            " LEFT JOIN instruments_statuses ON instruments.status_id=instruments_statuses.id" +
            " LEFT JOIN instruments_types ON instruments.instrument_type=instruments_types.type_id" +
            " WHERE instrument_id=?";
    static final String SQL_GET_INSTRUMENT_IMAGES = "SELECT instrument_image FROM instrument_images WHERE instrument_id=?";
    static final String SQL_ADD_INSTRUMENT = "INSERT INTO instruments(name, brand, country, price, rating," +
            " description, status_id, instrument_type) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    static final String SQL_UPDATE_INSTRUMENT = "UPDATE instruments SET name=?, brand=?, country=?, price=?," +
            " description=? WHERE instrument_id=?";
    static final String SQL_FIND_INSTRUMENT_BY_BRAND = "SELECT instrument_id, name, brand, country, price, rating," +
            " description, status, type FROM instruments LEFT JOIN instruments_statuses ON " +
            "instruments.status_id=id LEFT JOIN instruments_types ON instruments.instrument_type=type_id WHERE brand=?";
    static final String SQL_FIND_INSTRUMENT_BY_STATUS = "SELECT instrument_id, name, brand, country, price, rating," +
            " description, status, type FROM instruments LEFT JOIN instruments_statuses ON " +
            "instruments.status_id=id LEFT JOIN instruments_types ON instruments.instrument_type=type_id WHERE status=?";
    static final String SQL_FIND_INSTRUMENT_BY_TYPE = "SELECT instrument_id, name, brand, country, price, rating," +
            " description, status, type FROM instruments LEFT JOIN instruments_statuses ON " +
            "instruments.status_id=instruments_statuses.id LEFT JOIN instruments_types " +
            "ON instruments.instrument_type=instruments_types.type_id " +
            "WHERE instrument_type=?";
    static final String SQL_FIND_INSTRUMENT_BY_RATING = "SELECT instrument_id, name, brand, country, price, rating," +
            " description, status, type FROM instruments LEFT JOIN instruments_statuses ON " +
            "instruments.status_id=id LEFT JOIN instruments_types ON instruments.instrument_type=type_id WHERE rating=? and type=?";
    static final String SQL_IS_RATED_BY_USER_ID = "SELECT rating FROM ratings WHERE user_id=? and instrument_id=?";
    static final String SQL_IS_IN_BUCKET = "SELECT user_id, instrument_id FROM buckets WHERE user_id=? and instrument_id=?";
    static final String SQL_SET_INSTRUMENT_RATING = "INSERT INTO ratings(user_id, instrument_id, rating) VALUES(?, ?, ?)";
    static final String SQL_SET_INSTRUMENT_QUANTITY = "UPDATE buckets SET quantity=? WHERE user_id=? and instrument_id=?";
    static final String SQL_GET_INSTRUMENT_RATING = "SELECT rating FROM ratings WHERE instrument_id=?";
    static final String SQL_CONTROL_INSTRUMENT_STATUS = "UPDATE instruments SET status_id=? WHERE instrument_id=?";
    static final String SQL_CONTROL_INSTRUMENT_TYPE = "UPDATE instruments SET instrument_type=? WHERE instrument_id=?";
    static final String SQL_ADD_INSTRUMENT_TO_BUCKET = "INSERT INTO buckets(user_id, instrument_id, quantity) VALUES(?, ?, ?)";
    static final String SQL_REMOVE_INSTRUMENT_FROM_BUCKET = "DELETE FROM buckets WHERE(user_id=? and instrument_id=?)";
    static final String SQL_CLEAR_USER_BUCKET = "DELETE FROM buckets WHERE user_id=?";
    static final String SQL_FIND_ALL_ORDER_ITEMS = "SELECT instruments.instrument_id, name, brand, country, price, description, rating, status, type, quantity FROM instruments " +
            "LEFT JOIN instruments_statuses ON instruments.status_id=id " +
            "LEFT JOIN instruments_types ON instruments.instrument_type=type_id " +
            "LEFT JOIN buckets ON instruments.instrument_id=buckets.instrument_id WHERE user_id=?";
    static final String SQL_SET_INSTRUMENT_IMAGE = "INSERT INTO instrument_images(instrument_id, instrument_image) VALUES(?, ?)";
    static final String INSTRUMENT_ORDER_BY = " ORDER BY brand ";
    public static final String SQL_INSTRUMENT_ROW_COUNT_BY_TYPE = "SELECT COUNT(*) FROM instruments WHERE instrument_type=?";


    /**
     * Requests to 'orders' table into musician instruments database
     */
    static final String SQL_FIND_ALL_ORDERS = "SELECT order_id, user_id, order_date, address, status, price, payment FROM orders " +
            "LEFT JOIN order_statuses ON orders.status_id=order_statuses.status_id";
    static final String SQL_ADD_ORDER = "INSERT INTO orders(user_id, order_date, address, status_id, price, payment) VALUES(?, ?, ?, ?, ?, ?)";
    static final String SQL_FIND_ORDER_BY_ORDER_ID = "SELECT order_id, user_id, order_date, address, status, price, payment FROM orders " +
            "LEFT JOIN order_statuses ON orders.status_id=order_statuses.status_id WHERE order_id=?";
    static final String SQL_FIND_ORDER_BY_USER_ID = "SELECT order_id, user_id, order_date, address, status, price, payment FROM orders " +
            "LEFT JOIN order_statuses ON orders.status_id=order_statuses.status_id WHERE user_id=?";
    static final String SQL_ADD_ITEMS_TO_ORDER = "INSERT INTO order_items(order_id, item_id, quantity) VALUES(?, ?, ?)";
    static final String SQL_GET_ORDER_ITEMS = "SELECT instruments.instrument_id, name, brand, country, price, description, rating, status, type, quantity FROM instruments " +
            "LEFT JOIN instruments_statuses ON instruments.status_id=id " +
            "LEFT JOIN instruments_types ON instruments.instrument_type=type_id " +
            "LEFT JOIN order_items ON instruments.instrument_id=order_items.item_id WHERE order_id=?";
    static final String SQL_CHANGE_ORDER_STATUS = "UPDATE orders SET status_id=? WHERE order_id=?";
    static final String SQL_FIND_USER_ORDER_BY_STATUS = "SELECT order_id, user_id, order_date, address, status, price, payment FROM orders " +
            "LEFT JOIN order_statuses ON orders.status_id=order_statuses.status_id WHERE user_id=? and orders.status_id=?";
    static final String SQL_FIND_ORDER_BY_STATUS = "SELECT order_id, user_id, order_date, address, status, price, payment FROM orders " +
            "LEFT JOIN order_statuses ON orders.status_id=order_statuses.status_id WHERE orders.status_id=?";
    static final String SQL_ORDER_ROW_COUNT_BY_TYPE = "SELECT COUNT(*) FROM orders WHERE status_id=?";
    static final String ORDER_ORDER_BY = " ORDER BY order_date ";
}
