package com.shinkarev.finalproject.util;

import com.shinkarev.finalproject.command.client.CreateOrderCommand;
import jakarta.servlet.http.HttpServletRequest;


import java.util.HashMap;
import java.util.Map;

/**
 * This util class used by {@link CreateOrderCommand}
 */

public class OrderController {

    /**
     * This method used for
     * @param instrumentId - array of instrument id from user cart
     * @param quantity - array of instrument quantity from user cart
     * @return
     * @throws NumberFormatException
     */

    public static Map<Long, Integer> orderItemCreat(String[] instrumentId, String[] quantity) throws NumberFormatException {
        Map<Long, Integer> orderItems = new HashMap<>();
        for (int i = 0; i < instrumentId.length; i++) {
            orderItems.put(Long.parseLong(instrumentId[i]), Integer.parseInt(quantity[i]));
        }
        return orderItems;
    }

    /**
     * This method used for removing user cart items from session
     * after order creating
     * @param request - HttpServletRequest request
     * @param instrumentId - array of instrument id from user cart
     */

    public static void removeCartFromSession(HttpServletRequest request, String[] instrumentId) {
        for (String s : instrumentId) {
            request.getSession().removeAttribute(s);
        }
    }
}
