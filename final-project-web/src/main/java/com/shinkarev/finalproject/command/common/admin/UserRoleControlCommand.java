package com.shinkarev.finalproject.command.common.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserRoleControlCommand implements Command {
    private final Logger logger = LogManager.getLogger();
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String newStatus = request.getParameter("new_state");
        UserStatusType statusType = UserStatusType.valueOf(newStatus);
        UserServiceImpl userService = new UserServiceImpl();
        try {
            userService.userStatusController(Long.parseLong(userId), statusType);
        } catch (ServiceException e) {
            logger.log(Level.DEBUG, "Error. Impossible change status by this " + userId + " user");
//                    todo error to admin page
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(ParamName.REFERER));
        return router;
    }
}
