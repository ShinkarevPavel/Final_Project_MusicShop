package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class ShowInstrumentDetailsCommand implements Command {
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        System.out.println("command");
        String instrumentId = request.getParameter(INSTRUMENT_ID_PARAM);
        System.out.println(router.getPagePath());
        InstrumentService instrumentService = new InstrumentServiceImpl();
        try {
            Optional<Instrument> optionalInstrument = instrumentService.findInstrumentById(Long.parseLong(instrumentId));
            if (optionalInstrument.isPresent()) {
                Instrument instrument = optionalInstrument.get();
                request.setAttribute(INSTRUMENT_PARAM, instrument);
            } else {
                request.setAttribute(INSTRUMENT_DETAILS_ERROR, "Looks like there is no that instrument");
            }
            router.setPagePath(CLIENT_SHOW_INSTRUMENT_DETAILS);
        } catch (ServiceException ex) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong");
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}