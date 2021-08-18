package com.shinkarev.finalproject.util;

import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.Instrument;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.shinkarev.finalproject.command.ParamName.*;

public class CartController {

    public static Router cartQuantityControl(HttpServletRequest request, Map<Instrument, Integer> cartItems) {
        Router router = new Router();
        double summa = 0;
        for (Map.Entry<Instrument, Integer> item : cartItems.entrySet()) {
            summa += item.getKey().getPrice() * item.getValue();

        }
        request.setAttribute(TOTAL_CART, (Math.round(summa * 100d) / 100d));
        request.setAttribute(CART_ITEMS, cartItems);
        router.setPagePath(PageName.CLIENT_BUCKET_PAGE);
        return router;
    }
}
