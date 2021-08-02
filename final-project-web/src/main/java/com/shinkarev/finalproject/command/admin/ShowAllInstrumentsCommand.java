package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.shinkarev.finalproject.command.PageName.ERROR_PAGE;
import static com.shinkarev.finalproject.command.PageName.SHOW_INSTRUMENTS;
import static com.shinkarev.finalproject.command.ParamName.*;

public class ShowAllInstrumentsCommand implements Command {
    private Logger logger = LogManager.getLogger();
    private Router router = new Router();
    @Override
    public Router execute(HttpServletRequest request) {
        List<Instrument> instruments;
        InstrumentServiceImpl instrumentService = new InstrumentServiceImpl();

        try {
            instruments = instrumentService.getAllEntity();
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
