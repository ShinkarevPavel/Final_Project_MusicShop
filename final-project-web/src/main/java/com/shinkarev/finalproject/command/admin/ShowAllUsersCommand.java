package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Page;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.ServiceProvider;
import com.shinkarev.musicshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.musicshop.dao.BaseDao.PAGE_SIZE;

public class ShowAllUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        UserService service = ServiceProvider.USER_SERVICE;
        List<User> users;
        try {
            int pageToDisplay = getPage(request);
            users = service.readByPage(pageToDisplay);
            if (users.size() != 0) {
                int userCount = service.getUserCount();
                request.setAttribute(ParamName.USER_LIST_PARAM, users);
                request.setAttribute(PAGEABLE, new Page(userCount, pageToDisplay, PAGE_SIZE));
            } else {
                request.setAttribute(ADMIN_MESSAGE, LocaleSetter.getInstance().getMassage(PAGE_MESSAGE_ADMIN, locale));
            }
            router.setPagePath(SHOW_ALL_USERS);
        } catch (ServiceException | NumberFormatException ex) {
            logger.log(Level.ERROR, "Error of all users showing", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_CHANGE_DATA + ex.getMessage(), locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
