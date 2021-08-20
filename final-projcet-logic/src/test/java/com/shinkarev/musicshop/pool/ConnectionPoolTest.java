package com.shinkarev.musicshop.pool;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class ConnectionPoolTest {
    ConnectionPool connectionPool;
    List<Connection> connections;
    Connection connection;

    @BeforeClass
    public void before() {
        connectionPool = ConnectionPool.getInstance();
        connections = new ArrayList<>();
        connection = connectionPool.getConnection();
    }

    @Test
    public void testGetInstance() {
        Assert.assertNotNull(connection);
        connectionPool.releaseConnection(connection);
    }

    @Test
    public void testGetInstanceGetConnection() {
        for (int i = 0; i < 7; i++) {
            connections.add(connectionPool.getConnection());
        }
        int actual = connectionPool.getActiveConnectionsSize();
        int expected = 1;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetInstancePoolSize() {
        int actual = connectionPool.getActiveConnectionsSize() + connectionPool.getGivenConnectionsSize();
        int expected = 8;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetInstanceReturnConnection() {
        for (int i = 0; i < 7; i++) {
            connectionPool.releaseConnection(connections.get(i));
        }
        int actual = connectionPool.getActiveConnectionsSize();
        int expected = 8;
        Assert.assertEquals(actual, expected);
    }


    @AfterClass
    public void after() {
        ConnectionPool.getInstance().destroyPool();
        connections = null;
        connection = null;
    }
}