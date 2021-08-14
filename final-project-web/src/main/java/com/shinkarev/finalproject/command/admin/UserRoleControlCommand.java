package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class UserRoleControlCommand implements Command {
    private final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String userId = request.getParameter(USER_ID_PARAM);
        String newRole = request.getParameter(NEW_ROLE_PARAM);
        UserRoleType roleType = UserRoleType.valueOf(newRole);
        UserServiceImpl userService = new UserServiceImpl();
        try {
            if (!userService.userRoleController(Long.parseLong(userId), roleType)) {
                request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong");
                router.setPagePath(ERROR_PAGE);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.log(Level.DEBUG, "Error. Impossible change status by this " + userId + " user");
//                    todo error to admin page
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(ParamName.REFERER));
        return router;
    }
}
