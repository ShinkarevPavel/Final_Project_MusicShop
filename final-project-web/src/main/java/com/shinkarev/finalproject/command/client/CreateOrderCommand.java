package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.PriceCounter;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import com.shinkarev.musicshop.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;

import static com.shinkarev.finalproject.command.ParamName.*;

public class CreateOrderCommand implements Command {
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String userId = request.getParameter(USER_ID_PARAM);
        String address = request.getParameter(ParamName.ADDRESS_DELIVERY);
        List<Instrument> instruments;
        InstrumentServiceImpl instrumentService = new InstrumentServiceImpl();
        OrderServiceImpl orderService = new OrderServiceImpl();
        try {
            instruments = instrumentService.getUserBucket(Long.parseLong(userId));
            double oderPrice;
            if (instruments.size() != 0) {
                oderPrice = PriceCounter.getGeneralPrice(instruments);
                Order order = new Order(Long.parseLong(userId), LocalDateTime.now(), instruments, oderPrice, address, OderType.CREATED);
                if (orderService.addOrder(order)) {
                    // sent message on the orderConfirmation page that order was created
                    instrumentService.clearUserBucket(Long.parseLong(userId));
                } else {
                    // sent message on the orderConfirmation page that error happened
                }
            } else {
                // sent message on the orderConfirmation page that order bucket is empty
            }

//            router.setPagePath();
        } catch (ServiceException | NumberFormatException ex) {
            //todo
        }
        return router;
    }
}
