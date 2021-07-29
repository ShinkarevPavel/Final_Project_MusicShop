package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.shinkarev.finalproject.command.PageName.ERROR_PAGE;
import static com.shinkarev.finalproject.command.ParamName.*;

public class AddToBucketCommand implements Command {
    private Router router = new Router();
    @Override
    public Router execute(HttpServletRequest request) {
        String userId = request.getParameter(USER_ID_PARAM);
        String itemId = request.getParameter(INSTRUMENT_ID_PARAM);
        InstrumentServiceImpl instrumentService = new InstrumentServiceImpl();
        try {
            if (!instrumentService.addItemToBucket(Long.parseLong(userId), Long.parseLong(itemId))) {
                request.setAttribute(ERRORS_ON_ERROR_PAGE, "Item wasn't added to bucket");
                router.setPagePath(ERROR_PAGE);
            }
        } catch (ServiceException | NumberFormatException e) {
// Todo smt here
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(ParamName.REFERER));
        return router;
    }
}
