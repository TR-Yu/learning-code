package com.yu.synchornizedlock;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * 同步锁测试
 *
 * @author YU
 * @date 2022-06-11 16:46
 */
public class SynchronizedDemo {
    private static Logger logger = Logger.getLogger("SynchronizeDemo");
    public static void main(String[] args) throws InterruptedException {
        testLocksObject();
    }
    private static  void testThinLocksWith() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object lockObject = new Object();
        logger.info("未进入同步块, MarkWord :");
        logger.info(ClassLayout.parseInstance(lockObject).toPrintable());
        Runnable runnable = () -> {
            synchronized (lockObject) {
                logger.info(Thread.currentThread().getName() + "进入到同步块，MarkWord为： ");
                logger.info(ClassLayout.parseInstance(lockObject).toPrintable());
            }
        };

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        scheduledExecutorService.scheduleAtFixedRate(runnable,0,5,TimeUnit.SECONDS);
    }

    private static void testLocksObject() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object o = new Object();
        logger.info("未进入同步块, MarkWord :");
        logger.info(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            logger.info(Thread.currentThread().getName() + "进入到同步块，MarkWord为： ");
            logger.info(ClassLayout.parseInstance(o).toPrintable());
        }

        Thread thread = new Thread(() -> {
            synchronized (o) {
                logger.info(Thread.currentThread().getName() + "进入到同步块，MarkWord为： ");
                logger.info(ClassLayout.parseInstance(o).toPrintable());
            }
        });
        thread.start();
        thread.join();
        logger.info(Thread.currentThread().getName() + "进入到同步块，MarkWord为： ");
        logger.info(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            logger.info(Thread.currentThread().getName() + "进入到同步块，MarkWord为： ");
            logger.info(ClassLayout.parseInstance(o).toPrintable());
        }

        synchronized (o) {
            logger.info(Thread.currentThread().getName() + "进入到同步块，MarkWord为： ");
            logger.info(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
