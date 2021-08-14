package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import jakarta.servlet.http.HttpServletRequest;

import static com.shinkarev.finalproject.command.PageName.LOGIN_PAGE;


public class ToLoginPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(LOGIN_PAGE);
        return router;
    }
}
