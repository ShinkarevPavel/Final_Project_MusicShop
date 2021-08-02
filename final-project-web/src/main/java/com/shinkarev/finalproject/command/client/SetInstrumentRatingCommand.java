package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import jakarta.servlet.http.HttpServletRequest;

import static com.shinkarev.finalproject.command.ParamName.*;

public class SetInstrumentRatingCommand implements Command {
    private Router router = new Router();
    @Override
    public Router execute(HttpServletRequest request) {
        String mark = request.getParameter("mark");
        String instrumentId = request.getParameter(INSTRUMENT_ID_PARAM);
        String userId = request.getParameter(USER_ID_PARAM);


        return router;
    }
}
