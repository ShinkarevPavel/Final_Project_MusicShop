package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.UserService;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.shinkarev.finalproject.command.PageName.*;

public class ShowAllUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserServiceImpl service = new UserServiceImpl();
    private Router router = new Router();
    @Override
    public Router execute(HttpServletRequest request) {
        List<User> users;
        try {
            users = service.getAllEntity();
            request.setAttribute(ParamName.USER_LIST_PARAM, users);
            router.setPagePath(SHOW_ALL_USERS);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while client registration data", e);
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
