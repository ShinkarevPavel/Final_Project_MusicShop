package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class SetInstrumentRatingCommand implements Command {


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String mark = request.getParameter(ParamName.RATING);
        User user = (User) request.getSession().getAttribute(USER);
        Instrument instrument = (Instrument) request.getSession().getAttribute(INSTRUMENT_PARAM);
        InstrumentService instrumentService = new InstrumentServiceImpl();

        try {
            if (user != null && instrument != null) {

                if (instrumentService.isInBucket(user.getId(), instrument.getInstrument_id())) {
                    request.setAttribute(Long.toString(instrument.getInstrument_id()), instrument.getInstrument_id());
                }
                if (instrumentService.isRated(user.getId(), instrument.getInstrument_id())) {
                    request.setAttribute(RATING_MESSAGE, "u already rated it");
                } else {
                    instrumentService.addInstrumentRating(user.getId(), instrument.getInstrument_id(), Integer.parseInt(mark));
                    request.setAttribute(RATING_MESSAGE, "thanks for ur rating");
                }

            } else {
                request.setAttribute(RATING_MESSAGE, "for rating login");
            }
        } catch (ServiceException ex) {
            request.setAttribute(RATING_MESSAGE, "Oops. something went wrong. We will work with it...");
            router.setPagePath(ERROR_PAGE);
        }
        router.setPagePath(CLIENT_SHOW_INSTRUMENT_DETAILS);
        return router;
    }
}
