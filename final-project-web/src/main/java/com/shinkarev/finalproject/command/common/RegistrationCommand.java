package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.validator.Impl.RegistrationValidatorImp;
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
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {


        if (request.getMethod().equals(METHOD_POST)) {
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
            RegistrationValidatorImp registrationValidator = new RegistrationValidatorImp();
            Map<String, String> errors = registrationValidator.checkValues(registrationValues, locale);


            if (!errors.isEmpty()) {
                request.setAttribute(REGISTRATION_VALUES, registrationValues);
                request.setAttribute(ERRORS_LIST, errors);
                router.setPagePath(REGISTRATION_PAGE);
            } else {
                User user = new User(login, email, nickname, name, surename, UserStatusType.ACTIVE, UserRoleType.GUEST);
                UserDaoImpl userDao = new UserDaoImpl(); //todo to service !!!!
                try {
                    if (userDao.addUser(user, password)) {
                        request.getSession().setAttribute(USER, user);
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
