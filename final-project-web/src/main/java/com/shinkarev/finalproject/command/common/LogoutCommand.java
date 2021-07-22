package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        request.getSession().invalidate();
        router.setPagePath(PageName.INDEX_PAGE);
        return router;
    }
}
