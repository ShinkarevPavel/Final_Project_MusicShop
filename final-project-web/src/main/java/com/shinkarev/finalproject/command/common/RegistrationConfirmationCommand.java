package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.shinkarev.finalproject.command.PageName.*;

public class RegistrationConfirmationCommand implements Command {
    private Router router = new Router();
    @Override
    public Router execute(HttpServletRequest request) {
        String confirmationKey = request.getParameter(ParamName.CONFIRM_KEY);
        UserServiceImpl userService = new UserServiceImpl();
        try {
            Optional<User> userOptional = userService.getUserByRegistrationKey(confirmationKey);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                userService.userRoleController(user.getId(), UserRoleType.CLIENT);
                router.setPagePath(LOGIN_PAGE);
            } else {
                router.setPagePath(REGISTRATION_PAGE);
            }
        } catch (ServiceException e) {
            // todo error page
        }
        return router;
    }
}
