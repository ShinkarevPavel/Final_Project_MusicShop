package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.ServiceProvider;
import com.shinkarev.musicshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.shinkarev.finalproject.command.ParamName.*;

/**
 * {@link User} role control command.
 * Used by admin for changing users role.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */

public class UserRoleControlCommand implements Command {
    private final Logger logger = LogManager.getLogger();

    /**
     * @param request the HttpServletRequest
     * @return the {@link Router} that contains information about next page
     * and data that will be display on client's page.
     *
     * @throws ServiceException if the request could not be handled.
     */

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        String userId = request.getParameter(USER_ID_PARAM);
        String newRole = request.getParameter(NEW_ROLE_PARAM);
        UserService userService = ServiceProvider.USER_SERVICE;
        try {
            UserRoleType roleType = UserRoleType.valueOf(newRole);
            if (!userService.userRoleController(Long.parseLong(userId), roleType)) {
                request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
                router.setErrorCode(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException | NumberFormatException | IllegalStateException ex) {
            logger.log(Level.DEBUG, "Error. Impossible change role by this " + userId + " user", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE + ex.getMessage(), locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(ParamName.REFERER));
        return router;
    }
}
