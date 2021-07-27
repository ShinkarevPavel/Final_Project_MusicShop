package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.shinkarev.finalproject.command.PageName.ERROR_PAGE;
import static com.shinkarev.finalproject.command.ParamName.*;

public class InstrumentTypeControlCommand implements Command {
    private Logger logger = LogManager.getLogger();
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String instrumentId = request.getParameter(INSTRUMENT_ID_PARAM);
        String newType = request.getParameter(INSTRUMENT_NEW_TYPE_PARAM);

        InstrumentServiceImpl instrumentService = new InstrumentServiceImpl();
        InstrumentType type = InstrumentType.valueOf(newType);
        try {
            if (!instrumentService.instrumentTypeControl(Long.parseLong(instrumentId), type)) {
                request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong");
                router.setPagePath(ERROR_PAGE);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.log(Level.DEBUG, "Error. Impossible change role by this " + instrumentId + " user");
//                    todo error to admin page
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(ParamName.REFERER));
        return router;
    }
}
