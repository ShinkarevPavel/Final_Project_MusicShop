package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.validator.Impl.RegistrationValidatorImp;
import com.shinkarev.finalproject.validator.InputDataValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.PageName.REGISTRATION_PAGE;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.validator.UserValidator.*;

public class ChangePasswordCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String password = request.getParameter(PASSWORD.getFieldName());
        String checkPassword = request.getParameter(CHECKPASSWORD.getFieldName());
        String locale = (String) request.getSession().getAttribute(LOCALE);

        Map<String, String> registrationValues = new HashMap<>();
        registrationValues.put(PASSWORD.getFieldName(), password);
        registrationValues.put(CHECKPASSWORD.getFieldName(), checkPassword);
        InputDataValidator registrationValidator = new RegistrationValidatorImp();
        Map<String, String> errors = registrationValidator.checkValues(registrationValues, locale);

        if (!errors.isEmpty()) {
            request.setAttribute(REGISTRATION_VALUES, registrationValues);
            request.setAttribute(ERRORS_LIST, errors);
            router.setPagePath(PageName.CHANGE_PASSWORD_PAGE);
        } else {

        }


        return router;
    }
}
