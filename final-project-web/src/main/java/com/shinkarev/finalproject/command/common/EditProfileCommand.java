package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.validator.Impl.EditProfileValidatorImpl;
import com.shinkarev.finalproject.validator.InputDataValidator;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.UserService;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.validator.UserValidator.*;

public class EditProfileCommand implements Command {


    @Override
    public Router execute(HttpServletRequest request) {
        UserService userService = new UserServiceImpl();
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(USER);
        String nickname = request.getParameter(NICKNAME.getFieldName());
        String name = request.getParameter(NAME.getFieldName());
        String surename = request.getParameter(SURENAME.getFieldName());
        String locale = (String) request.getSession().getAttribute(LOCALE);

        Map<String, String> registrationValues = new HashMap<>();

        registrationValues.put(NICKNAME.getFieldName(), nickname);
        registrationValues.put(NAME.getFieldName(), name);
        registrationValues.put(SURENAME.getFieldName(), surename);

        String method = request.getMethod();
        if (method.equals(METHOD_POST)) {
            InputDataValidator dataValidator = new EditProfileValidatorImpl();
            Map<String, String> errors = dataValidator.checkValues(registrationValues, locale);
            if (!errors.isEmpty()) {
                request.setAttribute(NICKNAME.getFieldName(), errors.get(NICKNAME.getFieldName()));
                router.setPagePath(EDIT_PROFILE_PAGE);
            } else {
                user.setNickname(nickname);
                user.setName(name);
                user.setSurename(surename);
                router.setPagePath(EDIT_PROFILE_PAGE);
                try {
                    if (userService.updateUser(user)) {
                        request.setAttribute(ERRORS_ON_ERROR_PAGE, "Impossible update User");
                    }
                } catch (ServiceException e) {
//
                }
            }
        }
        return router;
    }
}
