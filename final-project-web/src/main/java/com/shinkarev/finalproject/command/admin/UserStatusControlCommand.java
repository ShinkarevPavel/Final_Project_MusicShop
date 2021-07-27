package com.shinkarev.finalproject.command.admin;

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

import static com.shinkarev.finalproject.command.PageName.ERROR_PAGE;
import static com.shinkarev.finalproject.command.ParamName.*;

public class UserStatusControlCommand implements Command {
    private final Logger logger = LogManager.getLogger();
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String userId = request.getParameter(USER_ID_PARAM);
        String newStatus = request.getParameter(NEW_STATUS_PARAM);
        UserStatusType statusType = UserStatusType.valueOf(newStatus);
        UserServiceImpl userService = new UserServiceImpl();
        try {
            if (!userService.userStatusController(Long.parseLong(userId), statusType)) {
                request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong");
                router.setPagePath(ERROR_PAGE);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.log(Level.DEBUG, "Error. Impossible change role by this " + userId + " user");
//                    todo error to admin page
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(ParamName.REFERER));
        return router;
    }
}
