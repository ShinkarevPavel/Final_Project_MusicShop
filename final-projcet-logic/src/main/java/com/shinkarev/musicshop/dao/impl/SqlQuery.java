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
    static final String SQL_CONTROL_USER_STATUS = "UPDATE users SET status_id=? WHERE user_id=?";
    static final String SQL_CONTROL_USER_ROLE = "UPDATE users SET role_id=? WHERE user_id=?";
    static final String SQL_ADD_USER = "INSERT INTO users(login, password, email, nickname, name, surename," +
            "  status_id, role_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
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
    /**
     * Requests to 'instruments' table into musician instruments database
     */
    static final String SQL_GET_ALL_INSTRUMENTS = "SELECT instrument_id, name, brand, country, price, description, rating, status, type FROM instruments" +
            " LEFT JOIN instruments_statuses ON instruments.status_id=id LEFT JOIN instruments_types ON instruments.instrument_type=type_id";
    static final String SQL_FIND_INSTRUMENT_BY_ID = "SELECT instrument_id, name, brand, country, price, description, rating, status, type FROM instruments" +
            " LEFT JOIN instruments_statuses ON instruments.status_id=id LEFT JOIN instruments_types ON instruments.instrument_type=type_id" +
            " WHERE instrument_id=?";
    static final String SQL_ADD_INSTRUMENT = "INSERT INTO instruments(name, brand, country, price, rating," +
            " description, status_id, instrument_type) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    static final String SQL_UPDATE_INSTRUMENT = "UPDATE instruments SET name=?, brand=?, country=?, price=?," +
            " description=?, rating=?, status_id=?, instrument_type=? WHERE instrument_id=?";
    static final String SQL_FIND_INSTRUMENT_BY_BRAND = "SELECT instrument_id, name, brand, country, price, rating," +
            " description, status, type FROM instruments LEFT JOIN instruments_statuses ON " +
            "instruments.status_id=id LEFT JOIN instruments_types ON instruments.instrument_type=type_id WHERE brand=?";
    static final String SQL_FIND_INSTRUMENT_BY_STATUS = "SELECT instrument_id, name, brand, country, price, rating," +
            " description, status, type FROM instruments LEFT JOIN instruments_statuses ON " +
            "instruments.status_id=id LEFT JOIN instruments_types ON instruments.instrument_type=type_id WHERE status=?";
    static final String SQL_FIND_INSTRUMENT_BY_TYPE = "SELECT instrument_id, name, brand, country, price, rating," +
            " description, status, type FROM instruments LEFT JOIN instruments_statuses ON " +
            "instruments.status_id=id LEFT JOIN instruments_types ON instruments.instrument_type=type_id WHERE type=?";
    static final String SQL_FIND_INSTRUMENT_BY_RATING = "SELECT instrument_id, name, brand, country, price, rating," +
            " description, status, type FROM instruments LEFT JOIN instruments_statuses ON " +
            "instruments.status_id=id LEFT JOIN instruments_types ON instruments.instrument_type=type_id WHERE rating=? and type=?";
    static final String SQL_GET_INSTRUMENT_RATING = "SELECT rating FROM ratings WHERE ratings.id=?";

    /**
     * Requests to 'instruments' table into musician instruments database
     */
    static final String SQL_FIND_ORDER_BY_ID = "SELECT id, user_id, order_time, price, ";
}
