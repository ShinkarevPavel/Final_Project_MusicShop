package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.shinkarev.finalproject.command.PageName.INDEX_PAGE;
import static com.shinkarev.finalproject.command.Router.RouterType.REDIRECT;


public class LogoutCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        request.getSession().invalidate();
        router.setRouterType(REDIRECT);
        logger.log(Level.DEBUG, request.getContextPath() + INDEX_PAGE);
        router.setPagePath(request.getContextPath() + INDEX_PAGE);
        return router;
    }
}
