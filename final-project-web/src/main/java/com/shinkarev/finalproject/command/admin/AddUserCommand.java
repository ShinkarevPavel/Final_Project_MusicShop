package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.RegistrationConfirmator;
import com.shinkarev.finalproject.validator.Impl.AdminRegistrationValidatorImpl;
import com.shinkarev.musicshop.dao.impl.UserDaoImpl;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.PageName.ADMIN_PAGE;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.command.ParamName.ERRORS_LIST;
import static com.shinkarev.finalproject.validator.UserValidator.*;

public class AddUserCommand implements Command {
    private Router router = new Router();
    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN.getFieldName());
        String password = request.getParameter(PASSWORD.getFieldName());
        String email = request.getParameter(EMAIL.getFieldName());
        String nickname = request.getParameter(NICKNAME.getFieldName());
        String locale = (String) request.getSession().getAttribute(LOCALE);

        Map<String, String> registrationValues = new HashMap<>();
        registrationValues.put(LOGIN.getFieldName(), login);
        registrationValues.put(PASSWORD.getFieldName(), password);
        registrationValues.put(EMAIL.getFieldName(), email);
        registrationValues.put(NICKNAME.getFieldName(), nickname);

        AdminRegistrationValidatorImpl registrationValidator = new AdminRegistrationValidatorImpl();
        Map<String, String> errors = registrationValidator.checkValues(registrationValues, locale);
        if (!errors.isEmpty()) {
            request.setAttribute(REGISTRATION_VALUES, registrationValues);
            request.setAttribute(ERRORS_LIST, errors);
//            router.setPagePath(TODO);
        } else {
            User user = new User(login, email, nickname, UserStatusType.ACTIVE, UserRoleType.GUEST);
            UserServiceImpl userService = new UserServiceImpl(); //todo to service !!!!
            try {
                String registrationKey = RegistrationConfirmator.setRegistrationToken(email, login);
                if (userService.addUser(user, password, registrationKey)) {
                    router.setPagePath(ADMIN_PAGE);
                } else {
                    //todo smt write here
                }
            } catch (ServiceException e) {
                //TODO
            }
        }


        return router;
    }
}
