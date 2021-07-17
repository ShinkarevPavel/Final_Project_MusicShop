package com.shinkarev.musicshop.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static Logger logger = LogManager.getLogger();
    private static final AtomicBoolean isInitialised = new AtomicBoolean(false);
    private static final AtomicBoolean isOnService = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private int POOL_SIZE;
    private int DEFAULT_POOL_SIZE = 5;
    private static final long TIMER_COUNTER_DELAY_IN_HOURS = 1;
    private static final long TIMER_COUNTER_REPEAT_IN_HOURS = 1;
    private String URL;
    private Properties properties;
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;
    private ReentrantLock connectionLocker;
    private Condition condition;
    private Timer timer;

    private ConnectionPool() {
        timer = new Timer();
        try {
            this.properties = ResourceManager.getInstance().getValue(ConnectionParameter.PROPERTY_PATH);
            this.URL = properties.getProperty(ConnectionParameter.URL);
            try {
                this.POOL_SIZE = Integer.parseInt(properties.getProperty(ConnectionParameter.POOL_SIZE));
            } catch (NumberFormatException ex) {
                logger.error("Can`t initialize pool size from property file. Poll size will initialise by default value", ex);
                this.POOL_SIZE = this.DEFAULT_POOL_SIZE;
            }
            this.freeConnections = new LinkedBlockingQueue<>(this.POOL_SIZE);
            this.givenAwayConnections = new LinkedBlockingQueue<>(this.POOL_SIZE);
            this.connectionLocker = new ReentrantLock();
            Class.forName(properties.getProperty(ConnectionParameter.DRIVER));
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(this.URL, this.properties);
                freeConnections.offer(new ProxyConnection(connection));
            }
            this.condition = connectionLocker.newCondition();
            timer.schedule(new PoolChecker(this.condition, this.connectionLocker),
                    TimeUnit.HOURS.toMillis(TIMER_COUNTER_DELAY_IN_HOURS), TimeUnit.HOURS.toMillis(TIMER_COUNTER_REPEAT_IN_HOURS));
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Fatal. Impossible create connection pool.", e);
        }
    }

    public static ConnectionPool getInstance() {
        while (instance == null) {
            if (isInitialised.compareAndSet(false, true)) {
                instance = new ConnectionPool();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        this.onService();
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error("Error getting connection ", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        this.onService();
        if (connection instanceof ProxyConnection) {
            givenAwayConnections.remove();
            freeConnections.offer((ProxyConnection) connection);
        } else {
            logger.error("Attention. Attempt to transfer to the Connection Pool rogue connection.");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                try {
                    freeConnections.take().reallyClose();
                } catch (SQLException ex) {
                    logger.error("Connection closing error ", ex);
                    Thread.currentThread().interrupt();
                }
            } catch (InterruptedException e) {
                logger.error("Connection getting error ", e);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
        this.timer.cancel();
        this.timer.purge();
    }

    boolean checkNumberConnection() {
        boolean isFull = true;
        int expectedPoolSize = freeConnections.size() + givenAwayConnections.size();
        if (expectedPoolSize != POOL_SIZE) {
            isFull = false;
            logger.error("Expected pool size differ from default pool size");
        }
        return isFull;
    }

    void addConnection() {
        int count = 0;
        int expectedPoolSize = freeConnections.size() + givenAwayConnections.size();
        while (expectedPoolSize != POOL_SIZE) {
            count++;
            try {
                Connection connection = DriverManager.getConnection(URL, properties);
                freeConnections.offer(new ProxyConnection(connection));
            } catch (SQLException ex) {
                logger.error("Connection can`t be created");
            }
        }
        logger.error(count + " connection(s) was added to pool");
    }

    private void onService() {
        while (isOnService.get()) {
            try {
                connectionLocker.lock();
                condition.await();
            } catch (InterruptedException e) {
                logger.error("Waiting time was interrupted", e);
            } finally {
                connectionLocker.unlock();
            }
        }
    }

    int getActiveConnectionsSize() {
        return this.freeConnections.size();
    }

    int getGivenConnectionsSize() {
        return this.givenAwayConnections.size();
    }

    void setServiceModeOn() {
        isOnService.set(true);
    }

    void setServiceModeOff() {
        isOnService.set(false);
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException ex) {
                logger.error("Driver deregister error ", ex);
            }
        });
    }
}
