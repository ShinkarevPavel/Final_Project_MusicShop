package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.*;
import com.shinkarev.finalproject.util.LocaleSetter;
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

public class ShowInstrumentByTypeCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String type = request.getParameter(ParamName.INSTRUMENT_TYPE);
        InstrumentService instrumentService = new InstrumentServiceImpl();
        List<Instrument> instruments;
        String locale = (String) request.getSession().getAttribute(LOCALE);
        try {
            int pageToDisplay = getPage(request);
            instruments = instrumentService.findInstrumentByType(InstrumentType.valueOf(type), pageToDisplay);
            int userCount = instruments.size();
            if (instruments.size() != 0) {
                request.setAttribute(INSTRUMENTS, instruments);
                request.setAttribute(PAGEABLE, new Page(userCount, pageToDisplay, PAGE_SIZE));
            } else {
                request.setAttribute(INSTRUMENTS_MESSAGE, LocaleSetter.getInstance().getMassage(EMPTY_INSTRUMENTS_LIST, locale));
            }
            router.setPagePath(PageName.CLIENT_SHOW_INSTRUMENT_PAGE);
        } catch (ServiceException | NumberFormatException e) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong.");
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
