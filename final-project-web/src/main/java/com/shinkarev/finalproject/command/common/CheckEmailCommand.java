package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.ForgotPasswordConfirmator;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.InputDataValidator;
import com.shinkarev.finalproject.validator.ValidatorProvider;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.ServiceProvider;
import com.shinkarev.musicshop.service.UserService;
import com.shinkarev.musicshop.util.PasswordHashGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.PageName.FORGOT_PASSWORD_PAGE;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.command.ParamName.PAGE_ERROR_ERROR_PAGE;
import static com.shinkarev.finalproject.validator.UserValidator.EMAIL;

/**
 * Forgot password command.
 * Used by users for changing password.
 * If input email is present {@link User} continue changing password
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */

public class CheckEmailCommand implements Command {

    /**
     * @param request the HttpServletRequest
     * @return the {@link Router} that contains information about next page
     * and data that will be display on client's page.
     * @throws ServiceException if the request could not be handled.
     */

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String method = request.getMethod();
        if (method.equals(METHOD_POST)) {
            String email = request.getParameter(EMAIL.getFieldName());
            String locale = (String) request.getSession().getAttribute(LOCALE);

            Map<String, String> registrationValues = new HashMap<>();
            registrationValues.put(EMAIL.getFieldName(), email);

            InputDataValidator passwordValidator = ValidatorProvider.FORGOT_PASSWORD_VALIDATOR;
            Map<String, String> errors;
            try {
                errors = passwordValidator.checkValues(registrationValues, locale);
                if (errors.isEmpty()) {
                    UserService userService = ServiceProvider.USER_SERVICE;
                    userService.setEmailToken(email, PasswordHashGenerator.encodePassword(email));
                    ForgotPasswordConfirmator.setRegistrationToken(email, email);
                    request.setAttribute(ADMIN_MESSAGE, LocaleSetter.getInstance().getMassage(PAGE_FORGOT_PASSWORD_EMAIL, locale));
                    router.setPagePath(FORGOT_PASSWORD_PAGE);
                } else {
                    request.setAttribute(ERRORS_LIST, errors);
                    router.setPagePath(FORGOT_PASSWORD_PAGE);
                }
            } catch (ServiceException ex) {
                logger.log(Level.ERROR, "Error of user registration", ex);
                request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
                router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            router.setPagePath(FORGOT_PASSWORD_PAGE);
        }
        return router;
    }
}
