package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class ToRegistrationIsDonePage implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(PageName.REGISTRATION_IS_DONE);
        return router;
    }
}
