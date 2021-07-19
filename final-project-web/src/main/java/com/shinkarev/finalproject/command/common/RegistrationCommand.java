package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.dao.impl.UserDaoImpl;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
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
        String nickname = request.getParameter(NAME.getFieldName());
        String name = request.getParameter(NAME.getFieldName());
        String surename = request.getParameter(SURENAME.getFieldName());

        Map<String, Boolean> service = new HashMap<>();
        service.put(login, false);
        service.put(password, false);
        service.put(email, false);
        service.put(nickname, false);


        UserServiceImpl userService = new UserServiceImpl();
        /*
         *This block checking login on RegEx conformity and unique
         */
        if (login != null && login.matches(LOGIN.getRegExp())) {
            if (userService.isLoginUnique(login)) {
                service.put(login, true);
                request.setAttribute(LOGIN.getFieldName(), login);
            } else {
                request.setAttribute(LOGIN_ERROR, MESSAGE_ERROR_LOGIN);
            }
        } else {
            request.setAttribute(LOGIN_ERROR, LOGIN.getMessage());
        }

        /*
         *This block checking password on RegEx conformity and checking equality of two entered passwords
         */
        if (password != null && password.matches(PASSWORD.getRegExp())) {
            if (password.equals(checkPassword)) {
                service.put(password, true);
                // TODO Q - is should send to jsp password if it is correct?
            } else {
                request.setAttribute(PASSWORD_ERROR, CHECKPASSWORD.getMessage());
            }
        } else {
            request.setAttribute(PASSWORD_ERROR, PASSWORD.getMessage());
        }

        /*
         *This block checking email on RegEx conformity and unique
         */
        if (email != null && email.matches(EMAIL.getRegExp())) {
            if (userService.isEmailUnique(email)) {
                service.put(email, true);
                request.setAttribute(EMAIL.getFieldName(), email);
            } else {
                request.setAttribute(EMAIL_ERROR, MESSAGE_ERROR_EMAIL);
            }
        } else {
            request.setAttribute(EMAIL_ERROR, EMAIL.getMessage());
        }

        /*
         *This block checking nickname on RegEx conformity
         */
        if (nickname != null && nickname.matches(NICKNAME.getRegExp())) {
            service.put(nickname, true);
            request.setAttribute(NICKNAME.getFieldName(), nickname);
        } else {
            request.setAttribute(NICKNAME_ERROR, NICKNAME.getMessage());
        }

        /*
         *This block checking name availability
         */
        if (name != null) {
            request.setAttribute(NAME.getFieldName(), name);
        }

        /*
         *This block checking sure availability
         */
        if (surename != null) {
            request.setAttribute(SURENAME.getFieldName(), surename);
        }

        if (!service.containsValue(false)) {
            User user = new User(login, email, nickname, name, surename, UserStatusType.ACTIVE, UserRoleType.CLIENT);
            UserDaoImpl userDao = new UserDaoImpl();
            try {
                userDao.addUser(user, password);
                router.setPagePath(REGISTRATION_IS_DONE);
            } catch (DaoException e) {
                //TODO
            }
        } else {
            router.setPagePath(REGISTRATION_PAGE);
        }
        return router;
    }
}
