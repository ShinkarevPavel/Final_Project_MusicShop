package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.dao.OrderDao;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.DaoException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Optional;

public class OrderDaoImplTest {
    private OrderDao orderDao;
    private String deliveryAddress;

    @BeforeTest
    public void before() {
        orderDao = new OrderDaoImpl();
        deliveryAddress = "Minsk, B. Rayt 14/152";
    }

    @Test(priority = 2)
    public void testFindEntityById() throws DaoException {
        Optional<Order> order = orderDao.findEntityById(39L);
        Assert.assertEquals(deliveryAddress, order.orElseThrow().getAddress());
    }

    @Test
    public void testChangeOrderStatusById() throws DaoException{
        Assert.assertTrue(orderDao.changeOrderStatusById(25L, OderType.DELIVERED));
    }

    @AfterTest
    public void after() {
        orderDao = null;
        deliveryAddress = null;
    }
}