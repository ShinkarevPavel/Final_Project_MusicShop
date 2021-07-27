package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class FindUserCommand implements Command {
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String userId = request.getParameter(ADMIN_PARAM_SEARCH_USER_BY_ID);
        if (userId != null) {
            request.getSession().setAttribute(ADMIN_PARAM_SEARCH_USER_BY_ID, userId);
        } else {
            userId = (String) request.getSession().getAttribute(ADMIN_PARAM_SEARCH_USER_BY_ID); // Todo is it crutch ????
        }
        User user;
        UserServiceImpl userService = new UserServiceImpl();
        try {
            Optional<User> optionalUser = userService.getUserById(Long.parseLong(userId));
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
                request.setAttribute(USER, user);
                router.setPagePath(USER_INFO_PAGE);
            } else {
                request.setAttribute(ERRORS_ON_ERROR_PAGE, "There is no user with ID " + userId);
                router.setPagePath(ERROR_PAGE);
            }
        } catch (ServiceException | NumberFormatException e) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "It is impossible to perform an action");
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
