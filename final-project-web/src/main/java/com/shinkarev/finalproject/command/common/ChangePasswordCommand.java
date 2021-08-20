package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.InputDataValidator;
import com.shinkarev.finalproject.validator.ValidatorProvider;
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
 * This command used by {@link User} for changing password from their cabinet
 */

public class ChangePasswordCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    /**
     * @param request the HttpServletRequest
     * @return the {@link Router} that contains information about next page
     * and data that will be display on client's page.
     * @throws ServiceException if the request could not be handled.
     */

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);

        User user = (User) request.getSession().getAttribute(USER);
        String password = request.getParameter(PASSWORD.getFieldName());
        String checkPassword = request.getParameter(CHECKPASSWORD.getFieldName());


        Map<String, String> registrationValues = new HashMap<>();
        registrationValues.put(PASSWORD.getFieldName(), password);
        registrationValues.put(CHECKPASSWORD.getFieldName(), checkPassword);
        InputDataValidator registrationValidator = ValidatorProvider.CHANGE_PASSWORD_VALIDATOR;

        String method = request.getMethod();
        if (method.equals(METHOD_POST)) {
            Map<String, String> errors;
            try {
                errors = registrationValidator.checkValues(registrationValues, locale);
                if (!errors.isEmpty()) {
                    request.setAttribute(REGISTRATION_VALUES, registrationValues);
                    request.setAttribute(ERRORS_LIST, errors);
                    router.setPagePath(CHANGE_PASSWORD_PAGE);
                } else {
                    UserService userService = ServiceProvider.USER_SERVICE;
                    if (userService.changePassword(user.getId(), password)) {
                        request.getSession().removeAttribute(USER);
                        router.setRouterType(Router.RouterType.REDIRECT);
                        router.setPagePath(PageName.REDIRECT_LOGIN_PAGE);
                    }
                }
            } catch (ServiceException | NumberFormatException ex) {
                logger.log(Level.ERROR, "Error of password changing", ex);
                request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
                router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            router.setPagePath(CHANGE_PASSWORD_PAGE);
        }
        return router;
    }
}
