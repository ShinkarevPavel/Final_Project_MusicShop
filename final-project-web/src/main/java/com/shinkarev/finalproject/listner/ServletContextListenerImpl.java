package com.shinkarev.finalproject.listner;

import com.shinkarev.musicshop.pool.ConnectionPool;
import jakarta.servlet.annotation.WebListener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;


@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.destroyPool();
    }
}
