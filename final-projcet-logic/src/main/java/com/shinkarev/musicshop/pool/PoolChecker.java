package com.shinkarev.musicshop.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class PoolChecker extends TimerTask {
    private static Logger logger = LogManager.getLogger();
    private ReentrantLock checkerLock;
    private Condition condition;

    PoolChecker(Condition condition, ReentrantLock locker) {
        checkerLock = locker;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(150);
            ConnectionPool.getInstance().setServiceModeOn();
            logger.info("Service mode was switched on");
            if (!ConnectionPool.getInstance().checkNumberConnection()) {
                ConnectionPool.getInstance().addConnection();
            }
            checkerLock.lock();
            condition.signalAll();
        } catch (InterruptedException e) {
            logger.error("Error. Sleeping time into TimeTask was interrupted", e);
        } finally {
            checkerLock.unlock();
            ConnectionPool.getInstance().setServiceModeOff();
            logger.info("Service mode was switched off");
        }
    }
}
