package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.dao.InstrumentDao;
import com.shinkarev.musicshop.dao.impl.InstrumentDaoImpl;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class ShowInstrumentByTypeCommand implements Command {

    private Router router = new Router();
    @Override
    public Router execute(HttpServletRequest request) {

        String instrumentType = request.getParameter(ParamName.INSTRUMENT_TYPE);
        InstrumentDao instrumentDao = new InstrumentDaoImpl();
        List<Instrument> instruments;
        String locale = (String) request.getSession().getAttribute(LOCALE);
        try {
            instruments = instrumentDao.findInstrumentByType(InstrumentType.valueOf(instrumentType));
            if (instruments.size() != 0) {
                request.setAttribute(INSTRUMENTS, instruments);
            } else {
                request.setAttribute(INSTRUMENTS_MESSAGE, LocaleSetter.getInstance().getMassage(EMPTY_INSTRUMENTS_LIST, locale));
            }
            router.setPagePath(PageName.CLIENT_SHOW_INSTRUMENT_PAGE);
        } catch (DaoException e) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong.");
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
