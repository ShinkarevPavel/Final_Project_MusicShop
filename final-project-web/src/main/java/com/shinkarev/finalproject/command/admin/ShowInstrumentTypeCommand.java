package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.*;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.musicshop.dao.BaseDao.PAGE_SIZE;

/**
 * Show all {@link Instrument}s by type command.
 * Used by admin for displaying instruments with certain type.
 * on admin page.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */

public class ShowInstrumentTypeCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    /**
     * @param request the HttpServletRequest
     * @return the {@link Router} that contains information about next page
     * and data that will be display on client's page.
     *
     * @throws ServiceException if the request could not be handled.
     */

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        request.setAttribute(MARK, MARK);
        String type = request.getParameter(INSTRUMENT_TYPE_PARAM);
        InstrumentService instrumentService = ServiceProvider.INSTRUMENT_SERVICE;

        List<Instrument> instruments;
        try {
            InstrumentType instrumentType = InstrumentType.valueOf(type);
            int pageToDisplay = getPage(request);
            instruments = instrumentService.findInstrumentByType(instrumentType, pageToDisplay);
            int instrumentCount = instrumentService.getInstrumentCount(instrumentType);
            if (instruments.size() != 0) {
                request.setAttribute(INSTRUMENTS, instruments);
                request.setAttribute(PAGEABLE, new Page(instrumentCount, pageToDisplay, PAGE_SIZE));
            } else {
                request.setAttribute(INSTRUMENTS_MESSAGE, LocaleSetter.getInstance().getMassage(PAGE_MESSAGE_ADMIN, locale));
            }
            router.setPagePath(SHOW_INSTRUMENTS);

        } catch (ServiceException | NumberFormatException | IllegalStateException ex) {
            logger.log(Level.ERROR, "Error of instruments type showing", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE + ex.getMessage(), locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
