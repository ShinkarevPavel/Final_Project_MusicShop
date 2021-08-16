package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class ToEditProfilePageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(PageName.EDIT_PROFILE_PAGE);
        return router;
    }
}
