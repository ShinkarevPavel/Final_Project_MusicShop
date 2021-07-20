package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.validator.RegistrationValidator;
import com.shinkarev.finalproject.validator.UserValidator;
import com.shinkarev.musicshop.dao.impl.UserDaoImpl;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.validator.UserValidator.*;

public class RegistrationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String login = request.getParameter(LOGIN.getFieldName());
        String password = request.getParameter(PASSWORD.getFieldName());
        String checkPassword = request.getParameter(CHECKPASSWORD.getFieldName());
        String email = request.getParameter(EMAIL.getFieldName());
        String nickname = request.getParameter(NICKNAME.getFieldName());
        String name = request.getParameter(NAME.getFieldName());
        String surename = request.getParameter(SURENAME.getFieldName());
        String locale = (String) request.getSession().getAttribute(LOCALE);

        Map<String, String> registrationValues = new HashMap<>();
        registrationValues.put(LOGIN.getFieldName(), login);
        registrationValues.put(PASSWORD.getFieldName(), password);
        registrationValues.put(CHECKPASSWORD.getFieldName(), checkPassword);
        registrationValues.put(EMAIL.getFieldName(), email);
        registrationValues.put(NICKNAME.getFieldName(), nickname);
        registrationValues.put(NAME.getFieldName(), name);
        registrationValues.put(SURENAME.getFieldName(), surename);

        Map<String, String> result = RegistrationValidator.checkValues(registrationValues, locale);
        if (request.getMethod().equals("POST")) {
            if (!result.isEmpty()) {
                for (Map.Entry<String, String> par : result.entrySet()) {
                    request.setAttribute(par.getKey(), par.getValue());
                }
                router.setPagePath(REGISTRATION_PAGE);
            } else {
                User user = new User(login, email, nickname, name, surename, UserStatusType.ACTIVE, UserRoleType.CLIENT);
                UserDaoImpl userDao = new UserDaoImpl();
                try {
                    if (userDao.addUser(user, password)) {
                        request.setAttribute(USER, user);
                        router.setPagePath(REGISTRATION_IS_DONE);
                    } else {
                        //todo smt write here
                    }
                } catch (DaoException e) {
                    //TODO
                }
            }
        }
        return router;
    }
}
