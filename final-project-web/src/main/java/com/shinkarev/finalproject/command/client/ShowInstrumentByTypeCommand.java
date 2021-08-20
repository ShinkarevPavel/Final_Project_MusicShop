package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.*;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.musicshop.dao.BaseDao.PAGE_SIZE;

/**
 * Show {@link Instrument}s by type command.
 * Used by clients for displaying certain {@link Instrument}s on the page.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */

public class ShowInstrumentByTypeCommand implements Command {
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
        String type = request.getParameter(ParamName.INSTRUMENT_TYPE);
        InstrumentService instrumentService = ServiceProvider.INSTRUMENT_SERVICE;
        List<Instrument> instruments;
        String locale = (String) request.getSession().getAttribute(LOCALE);
        try {
            int pageToDisplay = getPage(request);
            instruments = instrumentService.findInstrumentByType(InstrumentType.valueOf(type), pageToDisplay);
            int userCount = instrumentService.getInstrumentCount(InstrumentType.valueOf(type));
            if (instruments.size() != 0) {
                request.setAttribute(INSTRUMENTS, instruments);
                request.setAttribute(PAGEABLE, new Page(userCount, pageToDisplay, PAGE_SIZE));
            } else {
                request.setAttribute(INSTRUMENTS_MESSAGE, LocaleSetter.getInstance().getMassage(EMPTY_INSTRUMENTS_LIST, locale));
            }
            router.setPagePath(PageName.CLIENT_SHOW_INSTRUMENT_PAGE);
        } catch (ServiceException | NumberFormatException | IllegalStateException ex) {
            logger.log(Level.ERROR, "Error with instrument showing", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
