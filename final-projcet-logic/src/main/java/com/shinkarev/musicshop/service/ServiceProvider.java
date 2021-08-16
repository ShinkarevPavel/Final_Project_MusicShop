package com.shinkarev.musicshop.service;

import com.shinkarev.musicshop.service.impl.EmailServiceImpl;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import com.shinkarev.musicshop.service.impl.OrderServiceImpl;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;

public class ServiceProvider {
    public static final UserService USER_SERVICE = UserServiceImpl.getInstance();
    public static final InstrumentService INSTRUMENT_SERVICE = InstrumentServiceImpl.getInstance();
    public static final OrderService ORDER_SERVICE = OrderServiceImpl.getInstance();
    public static final EmailService EMAIL_SERVICE = EmailServiceImpl.getInstance();

    private ServiceProvider() {
    }
}
