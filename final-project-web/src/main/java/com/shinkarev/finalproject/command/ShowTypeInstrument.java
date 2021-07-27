package com.shinkarev.finalproject.command;

import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class ShowTypeInstrument implements Command{
    private Router router = new Router();
    @Override
    public Router execute(HttpServletRequest request) {
        String type = request.getParameter(INSTRUMENT_TYPE);
        InstrumentServiceImpl instrumentService = new InstrumentServiceImpl();
        InstrumentType instrumentType = InstrumentType.valueOf(type);
        List<Instrument> instruments;
        try {
            instruments = instrumentService.findInstrumentByType(instrumentType);
            if (instruments.size() != 0) {
                request.setAttribute(INSTRUMENTS, instruments);
            } else {
                request.setAttribute(INSTRUMENTS_MESSAGE, "Found nothing");
            }
            router.setPagePath(SHOW_INSTRUMENTS);
        } catch (ServiceException e) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Error getting instrument");
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
