package com.shinkarev.musicshop.service;

import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.entity.User;

/**
 * The {@link EmailService} used for sending messages to {@link User}
 */

public interface EmailService {
    boolean sendEmail (String emailTo, String address) throws ServiceException;
}
