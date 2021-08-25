package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.shinkarev.musicshop.dao.impl.UserField.*;

class UserCreator {
    private static Logger logger = LogManager.getLogger();
    private UserCreator() {
    }

    static User createUser(ResultSet resultSet) throws DaoException {
        User currentUser = new User();
        try {
            currentUser.setId(resultSet.getLong(ID));
            currentUser.setLogin(resultSet.getString(LOGIN));
            currentUser.setEmail(resultSet.getString(EMAIL));
            currentUser.setNickname(resultSet.getString(NICKNAME));
            currentUser.setName(resultSet.getString(NAME));
            currentUser.setSurename(resultSet.getString(SURENAME));
            currentUser.setStatus(UserStatusType.valueOf(resultSet.getString(STATUS_ID)));
            currentUser.setRole(UserRoleType.valueOf(resultSet.getString(ROLE_ID)));
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error. Impossible create user", ex);
            throw new DaoException("Error. Impossible create user", ex);
        }
        return currentUser;
    }
}
