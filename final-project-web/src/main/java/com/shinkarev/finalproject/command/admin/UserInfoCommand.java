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

public class UserInfoCommand implements Command {
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String userId = request.getParameter(USER_ID_PARAM);
        UserServiceImpl userService = new UserServiceImpl();
        User user;
        try {
            Optional<User> optionalUser = userService.getUserById(Long.parseLong(userId));
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
                request.setAttribute(USER, user);
                router.setPagePath(USER_INFO_PAGE);
            } else {
                request.setAttribute("error", "User not found");
                router.setPagePath(ERROR_PAGE);
            }
        } catch (ServiceException | NumberFormatException ex) {
            request.setAttribute("error", "Impossible get user info");
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
