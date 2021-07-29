package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.shinkarev.finalproject.command.ParamName.*;

public class ClearBucketCommand implements Command {
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String userId = request.getParameter(USER_ID_PARAM);
        InstrumentServiceImpl instrumentService = new InstrumentServiceImpl();
        try {
            if (!instrumentService.clearUserBucket(Long.parseLong(userId))) {
                // message on bucket page
            }
            // router set redirect on bucket
        } catch (ServiceException | NumberFormatException ex) {
            // todo write smt here
        }
        return router;
    }
}
