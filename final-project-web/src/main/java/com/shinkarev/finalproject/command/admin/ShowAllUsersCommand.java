package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Page;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.UserService;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.musicshop.dao.BaseDao.PAGE_SIZE;

public class ShowAllUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserServiceImpl service = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        List<User> users;
        try {
            int pageToDisplay = getPage(request);
            users = service.readByPage(pageToDisplay);
            if (users.size() != 0) {
                int userCount = service.getUserCount();
                request.setAttribute(ParamName.USER_LIST_PARAM, users);
                request.setAttribute(PAGEABLE, new Page(userCount, pageToDisplay, PAGE_SIZE));
            } else {
                request.setAttribute(ADMIN_MESSAGE, "Found nothing");
            }
            router.setPagePath(SHOW_ALL_USERS);
        } catch (ServiceException | NumberFormatException ex) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Error with loading users form db. Reason: " + ex.getMessage());
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
