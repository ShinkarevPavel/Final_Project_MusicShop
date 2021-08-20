package com.shinkarev.musicshop.pool;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPoolSecondTest {
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
    public void testConnectionPoolCloseConnection() throws SQLException {
        connectionPool.releaseConnection(connection);
        int actual = connectionPool.getActiveConnectionsSize() + connectionPool.getGivenConnectionsSize();
        int expected = 8;
        Assert.assertEquals(actual, expected);
    }

    @AfterClass
    public void after() {
        connectionPool.destroyPool();
        connections = null;
        connection = null;
    }
}
