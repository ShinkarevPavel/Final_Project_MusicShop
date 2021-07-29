package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.shinkarev.finalproject.command.ParamName.*;

public class CheckBucketCommand implements Command {
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String userId = request.getParameter(USER_ID_PARAM);

        InstrumentServiceImpl instrumentService = new InstrumentServiceImpl();
        List<Instrument> itemsFromBucket;
        try {
            itemsFromBucket = instrumentService.getUserBucket(Long.parseLong(userId));
            if (itemsFromBucket.size() != 0) {
                // sent on the Bucket page list instruments
            } else {
                // sent on bucket page message about - absence orders
            }
//            and set router -> bucket page
        } catch (ServiceException e) {
//            Todo
        }

        return router;
    }
}
