package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class ShowInstrumentDetailsCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String instrumentId = request.getParameter(INSTRUMENT_ID_PARAM);
        InstrumentService instrumentService = new InstrumentServiceImpl();
        try {
            Optional<Instrument> optionalInstrument = instrumentService.findInstrumentById(Long.parseLong(instrumentId));
            if (optionalInstrument.isPresent()) {
                Instrument instrument = optionalInstrument.get();
                User user = (User) request.getSession().getAttribute(USER);
                if (user != null) {
                    if (instrumentService.isInBucket(user.getId(), instrument.getInstrument_id())) {
                        request.setAttribute(instrumentId, instrument.getInstrument_id());
                    }
                    request.setAttribute(INSTRUMENT_PARAM, instrument);
                }
                request.getSession().setAttribute(INSTRUMENT_PARAM, instrument);
            } else {
                request.setAttribute(INSTRUMENT_DETAILS_ERROR, "Looks like there is no that instrument");
            }
            router.setPagePath(CLIENT_SHOW_INSTRUMENT_DETAILS);
        } catch (ServiceException ex) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong. We fix it, later ;)");
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}