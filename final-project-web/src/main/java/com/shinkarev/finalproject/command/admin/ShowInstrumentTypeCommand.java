package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.*;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.musicshop.dao.BaseDao.PAGE_SIZE;

public class ShowInstrumentTypeCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        request.setAttribute(MARK, MARK);
        String type = request.getParameter(INSTRUMENT_TYPE_PARAM);
        InstrumentService instrumentService = new InstrumentServiceImpl();

        List<Instrument> instruments;
        try {
            InstrumentType instrumentType = InstrumentType.valueOf(type);
            int pageToDisplay = getPage(request);
            instruments = instrumentService.findInstrumentByType(instrumentType, pageToDisplay);
            int instrumentCount = instruments.size();
            if (instruments.size() != 0) {
                request.setAttribute(INSTRUMENTS, instruments);
                request.setAttribute(PAGEABLE, new Page(instrumentCount, pageToDisplay, PAGE_SIZE));
            } else {
                request.setAttribute(INSTRUMENTS_MESSAGE, "Found nothing");
            }
            router.setPagePath(SHOW_INSTRUMENTS);

        } catch (ServiceException | NumberFormatException e) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Error getting instrument");
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
