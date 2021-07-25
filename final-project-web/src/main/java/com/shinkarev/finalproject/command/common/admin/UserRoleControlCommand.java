package com.shinkarev.finalproject.command.common.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.shinkarev.finalproject.command.ParamName.*;

public class UserRoleControlCommand implements Command {
    private final Logger logger = LogManager.getLogger();
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String userId = request.getParameter(USER_ID_PARAM);
        String newRole = request.getParameter(NEW_ROLE_PARAM);
        UserRoleType roleType = UserRoleType.valueOf(newRole);
        System.out.println(roleType);
        UserServiceImpl userService = new UserServiceImpl();
        try {
            userService.userRoleController(Long.parseLong(userId), roleType);
        } catch (ServiceException e) {
            logger.log(Level.DEBUG, "Error. Impossible change status by this " + userId + " user");
//                    todo error to admin page
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(ParamName.REFERER));
        return router;
    }
}
