package com.shinkarev.finalproject.command.common.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.shinkarev.finalproject.command.PageName.ERROR_PAGE;
import static com.shinkarev.finalproject.command.PageName.USER_INFO_PAGE;
import static com.shinkarev.finalproject.command.ParamName.USER;
import static com.shinkarev.finalproject.validator.UserValidator.NICKNAME;

public class FindUserCommand implements Command {
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String nickname = request.getParameter(NICKNAME.getFieldName());
        User user;
        UserServiceImpl userService = new UserServiceImpl();
        try {
            Optional<User> optionalUser = userService.getUserByNickName(nickname);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
                request.setAttribute(USER, user);
                router.setPagePath(USER_INFO_PAGE);
            } else {
                request.setAttribute("error", "There is no user with nickname " + nickname);
                router.setPagePath(ERROR_PAGE);
            }
        } catch (ServiceException e) {
            request.setAttribute("error.message", "Impossible take action");
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
