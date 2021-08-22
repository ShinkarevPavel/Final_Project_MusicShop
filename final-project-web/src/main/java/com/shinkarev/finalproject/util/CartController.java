package com.shinkarev.finalproject.util;

import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;
import com.shinkarev.finalproject.command.client.*;

import static com.shinkarev.finalproject.command.ParamName.*;

/**
 * This util class used by
 * {@link RemoveFromCartCommand}, {@link QuantityControlCommand}, {@link OrderProcessingCommand}
 * {@link CreateOrderCommand}, {@link CheckCartCommand}, {@link BuyNowCommand}, {@link AddToCartCommand}
 * for getting details about users cart
 */

public class CartController {

    public static void cartQuantityControl(HttpServletRequest request, long userId) throws ServiceException {
        InstrumentService instrumentService = ServiceProvider.INSTRUMENT_SERVICE;
        double summa = 0;
        Map<Instrument, Integer> cartItems = instrumentService.getUserBucket(userId);
        for (Map.Entry<Instrument, Integer> item : cartItems.entrySet()) {
            summa += item.getKey().getPrice() * item.getValue();

        }
        request.setAttribute(TOTAL_CART, (Math.round(summa * 100d) / 100d));
        request.setAttribute(CART_ITEMS, cartItems);
    }
}
