package com.shinkarev.musicshop.service;

import com.shinkarev.musicshop.entity.Entity;
import com.shinkarev.musicshop.exception.ServiceException;

import java.util.List;

public interface Service<K, T extends Entity> {
    List<T> getAllEntity() throws ServiceException;
}
