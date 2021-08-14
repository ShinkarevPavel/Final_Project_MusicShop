package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.RegistrationConfirmator;
import com.shinkarev.finalproject.validator.Impl.AdminRegistrationValidatorImpl;
import com.shinkarev.finalproject.validator.InputDataValidator;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.UserService;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.validator.UserValidator.*;

public class AddUserCommand implements Command {
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN.getFieldName());
        String password = request.getParameter(PASSWORD.getFieldName());
        String email = request.getParameter(EMAIL.getFieldName());
        String nickname = request.getParameter(NICKNAME.getFieldName());
        String name = request.getParameter(NAME.getFieldName());
        String surename = request.getParameter(SURENAME.getFieldName());
        String locale = (String) request.getSession().getAttribute(LOCALE);

        Map<String, String> registrationValues = new HashMap<>();
        registrationValues.put(LOGIN.getFieldName(), login);
        registrationValues.put(PASSWORD.getFieldName(), password);
        registrationValues.put(EMAIL.getFieldName(), email);
        registrationValues.put(NICKNAME.getFieldName(), nickname);
        registrationValues.put(NAME.getFieldName(), name);
        registrationValues.put(SURENAME.getFieldName(), surename);

        InputDataValidator registrationValidator = new AdminRegistrationValidatorImpl();
        String method = request.getMethod();
        if (method.equals(METHOD_POST)) {
            Map<String, String> errors;
            try {
                errors = registrationValidator.checkValues(registrationValues, locale);
                if (!errors.isEmpty()) {
                    request.setAttribute(REGISTRATION_VALUES, registrationValues);
                    request.setAttribute(ERRORS_LIST, errors);
                } else {
                    User user = new User(login, email, nickname, UserStatusType.ACTIVE, UserRoleType.GUEST);
                    UserService userService = new UserServiceImpl();
                    String registrationKey = RegistrationConfirmator.setRegistrationToken(email, login);
                    if (userService.addUser(user, password, registrationKey)) {
                        request.setAttribute(ADMIN_PAGE_MESSAGE, "User has been added");
                    } else {
                        request.setAttribute(ADMIN_PAGE_MESSAGE, "User has not been added");
                    }
                    router.setPagePath(ADMIN_PAGE);
                }
            } catch (ServiceException ex) {
                request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops");
                router.setPagePath(ERROR_PAGE);
            }
        } else {
            router.setPagePath(ADD_USER);
        }
        return router;
    }
}
