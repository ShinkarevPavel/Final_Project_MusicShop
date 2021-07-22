package com.shinkarev.musicshop.service;

import com.shinkarev.musicshop.exception.ServiceException;

public interface EmailService {
    boolean sendEmail (String emailTo, String address) throws ServiceException;
}
