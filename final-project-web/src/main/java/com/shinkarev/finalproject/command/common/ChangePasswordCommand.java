package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.validator.Impl.ChangePasswordValidatorImpl;
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

public class ChangePasswordCommand implements Command {
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(USER);
        String password = request.getParameter(PASSWORD.getFieldName());
        String checkPassword = request.getParameter(CHECKPASSWORD.getFieldName());
        String locale = (String) request.getSession().getAttribute(LOCALE);

        Map<String, String> registrationValues = new HashMap<>();
        registrationValues.put(PASSWORD.getFieldName(), password);
        registrationValues.put(CHECKPASSWORD.getFieldName(), checkPassword);
        InputDataValidator registrationValidator = new ChangePasswordValidatorImpl();

        String method = request.getMethod();
        if (method.equals(METHOD_POST)) {
            Map<String, String> errors = registrationValidator.checkValues(registrationValues, locale);
            if (!errors.isEmpty()) {
                request.setAttribute(REGISTRATION_VALUES, registrationValues);
                request.setAttribute(ERRORS_LIST, errors);
                router.setPagePath(PageName.CHANGE_PASSWORD_PAGE);
            } else {
                try {
                    if (userService.changePassword(user.getId(), password)) {
                        request.getSession().removeAttribute(USER);
                        router.setPagePath(LOGIN_PAGE);
                    }
                } catch (ServiceException ex) {
                    request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops. something went wrong. We will work with it...");
                    router.setPagePath(ERROR_PAGE);
                }
            }
        } else {
            router.setPagePath(CHANGE_PASSWORD_PAGE);
        }
        return router;
    }
}
