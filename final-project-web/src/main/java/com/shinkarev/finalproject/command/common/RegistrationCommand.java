package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.util.RegistrationConfirmator;
import com.shinkarev.finalproject.validator.InputDataValidator;
import com.shinkarev.finalproject.validator.ValidatorProvider;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.ServiceProvider;
import com.shinkarev.musicshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.validator.UserValidator.*;

/**
 * Registration command.
 * Used by users for registration on website.
 * Command get entered data and validates them.
 * If input data are correct {@link User} get confirmation email
 * that registration is done and an invitation for confirm registration on their email.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */

public class RegistrationCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    /**
     * @param request the HttpServletRequest
     * @return the {@link Router} that contains information about next page
     * and data that will be display on client's page.
     *
     * @throws ServiceException if the request could not be handled.
     */


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


        String method = request.getMethod();
        if (method.equals(METHOD_POST)) {
            InputDataValidator registrationValidator = ValidatorProvider.REGISTRATION_VALIDATOR;
            Map<String, String> errors;
            try {
                errors = registrationValidator.checkValues(registrationValues, locale);
                if (!errors.isEmpty()) {
                    request.setAttribute(REGISTRATION_VALUES, registrationValues);
                    request.setAttribute(ERRORS_LIST, errors);
                    router.setPagePath(REGISTRATION_PAGE);
                } else {
                    User user = new User(login, email, nickname, name, surename, UserStatusType.ACTIVE, UserRoleType.GUEST);
                    UserService userService = ServiceProvider.USER_SERVICE;

                    String registrationKey = RegistrationConfirmator.setRegistrationToken(email, login);
                    if (userService.addUser(user, password, registrationKey)) {
                        router.setRouterType(Router.RouterType.REDIRECT);
                        router.setPagePath(REDIRECT_REGISTRATION_IS_DONE);
                    } else {
                        request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
                        router.setErrorCode(HttpServletResponse.SC_BAD_REQUEST);
                    }
                }
            } catch (ServiceException ex) {
                logger.log(Level.ERROR, "Error of user registration", ex);
                request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
                router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            router.setPagePath(REGISTRATION_PAGE);
        }
        return router;
    }
}
