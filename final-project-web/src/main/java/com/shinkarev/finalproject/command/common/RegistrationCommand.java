package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.RegistrationValidator;
import com.shinkarev.musicshop.dao.impl.UserDaoImpl;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.core.config.plugins.validation.validators.RequiredValidator;

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

        Map<String, Boolean> service = new HashMap<>();
        service.put(login, false);
        service.put(password, false);
        service.put(email, false);
        service.put(nickname, false);

        Map<String, String> registrationValues = new HashMap<>();
        registrationValues.put("login", login);
        registrationValues.put("password", password);
        registrationValues.put("checkpasswrod", checkPassword);
        registrationValues.put("email", email);
        registrationValues.put("nickname", nickname);
        registrationValues.put("name", name);
        registrationValues.put("surename", surename);


        Map<String, String> result = RegistrationValidator.checkValues(registrationValues, locale);

        if (request.getMethod().equals("POST")) {
            if (result != null) {
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



            /*
             *This block checking password on RegEx conformity and checking equality of two entered passwords
             */
            if (password != null && password.matches(PASSWORD.getRegExp())) {
                if (password.equals(checkPassword)) {
                    service.put(password, true);
                } else {
                    request.setAttribute(PASSWORD_ERROR, LocaleSetter.getInstance().getMassage(CHECKPASSWORD.getMessage(), locale));
                }
            } else {
                request.setAttribute(PASSWORD_ERROR, LocaleSetter.getInstance().getMassage(PASSWORD.getMessage(), locale));
            }

            /*
             *This block checking email on RegEx conformity and unique
             */
            if (email != null && email.matches(EMAIL.getRegExp())) {
                if (userService.isEmailUnique(email)) {
                    service.put(email, true);
                    request.setAttribute(EMAIL.getFieldName(), email);
                } else {
                    request.setAttribute(EMAIL_ERROR, LocaleSetter.getInstance().getMassage(MESSAGE_ERROR_EMAIL, locale));
                }
            } else {
                request.setAttribute(EMAIL_ERROR, LocaleSetter.getInstance().getMassage(EMAIL.getMessage(), locale));
            }

            /*
             *This block checking nickname on RegEx conformity
             */
            if (nickname != null && nickname.matches(NICKNAME.getRegExp())) {
                service.put(nickname, true);
                request.setAttribute(NICKNAME.getFieldName(), nickname);
            } else {
                request.setAttribute(NICKNAME_ERROR, LocaleSetter.getInstance().getMassage(NICKNAME.getMessage(), locale));
            }

            /*
             *This block checking name availability
             */
            if (name != null) {
                request.setAttribute(NAME.getFieldName(), name);
            }

        }
        return router;
    }
}
