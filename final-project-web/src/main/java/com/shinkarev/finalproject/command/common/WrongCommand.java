package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import jakarta.servlet.http.HttpServletRequest;

import static com.shinkarev.finalproject.command.PageName.ERROR_PAGE;
import static com.shinkarev.finalproject.command.Router.RouterType.REDIRECT;

public class WrongCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(ERROR_PAGE);
        router.setRouterType(REDIRECT);
        return router;
    }
}
