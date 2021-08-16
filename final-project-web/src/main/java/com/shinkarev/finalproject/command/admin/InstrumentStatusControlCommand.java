package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.InstrumentStatusType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.shinkarev.finalproject.command.ParamName.*;

public class InstrumentStatusControlCommand implements Command {
    private Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        String instrumentId = request.getParameter(INSTRUMENT_ID_PARAM);
        String newStatus = request.getParameter(INSTRUMENT_NEW_STATUS_PARAM);

        InstrumentService instrumentService = ServiceProvider.INSTRUMENT_SERVICE;
        try {
            InstrumentStatusType statusType = InstrumentStatusType.valueOf(newStatus);
            if (!instrumentService.instrumentStatusControl(Long.parseLong(instrumentId), statusType)) {
                request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
                router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (ServiceException | NumberFormatException | IllegalStateException ex) {
            logger.log(Level.DEBUG, "Error. Impossible change status for this " + instrumentId + " instrument", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_CHANGE_DATA + ex.getMessage(), locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(ParamName.REFERER));
        return router;
    }
}
