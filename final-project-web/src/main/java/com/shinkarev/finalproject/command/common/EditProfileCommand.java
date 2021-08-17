package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.InputDataValidator;
import com.shinkarev.finalproject.validator.ValidatorProvider;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.User;
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
 * Edit profile command.
 * Used by clients for editing their personal data.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */

public class EditProfileCommand implements Command {
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
        User user = (User) request.getSession().getAttribute(USER);
        String nickname = request.getParameter(NICKNAME.getFieldName());
        String name = request.getParameter(NAME.getFieldName());
        String surename = request.getParameter(SURENAME.getFieldName());
        String locale = (String) request.getSession().getAttribute(LOCALE);
        UserService userService = ServiceProvider.USER_SERVICE;

        Map<String, String> registrationValues = new HashMap<>();

        registrationValues.put(NICKNAME.getFieldName(), nickname);
        registrationValues.put(NAME.getFieldName(), name);
        registrationValues.put(SURENAME.getFieldName(), surename);

        String method = request.getMethod();
        if (method.equals(METHOD_POST)) {
            InputDataValidator dataValidator = ValidatorProvider.EDIT_PROFILE_VALIDATOR;
            Map<String, String> errors;
            try {
                errors = dataValidator.checkValues(registrationValues, locale);
                if (!errors.isEmpty()) {
                    request.setAttribute(NICKNAME.getFieldName(), errors.get(NICKNAME.getFieldName()));
                    router.setPagePath(EDIT_PROFILE_PAGE);
                } else {
                    user.setNickname(nickname);
                    user.setName(name);
                    user.setSurename(surename);
                    if (userService.updateUser(user)) {
                        router.setPagePath(CABINET_PAGE);
                    }
                }
            } catch (ServiceException ex) {
                logger.log(Level.ERROR, "Error of editing profile", ex);
                request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
                router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        return router;
    }
}
