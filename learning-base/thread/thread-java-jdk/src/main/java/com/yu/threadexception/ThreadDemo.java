package com.yu.threadexception;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * try catch block 只能捕获到 ，线程异常测试
 *
 * @author YU
 * @date 2022-06-03 14:41
 */
public class ThreadDemo {
    public static void main(String[] args) {
        /*testCountDownLatch();*/
        testLockState();
    }

    /**
     * 测试try {} catch {} 的作用的范围
     */
    private static void testExceptionInThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello thread");
                int i = 2 / 0;
            }
        };
        Thread thread = new Thread(runnable);
        try {
            thread.start();
        } catch (Exception ex) {
            System.out.println("捕获到异常了");
            ex.printStackTrace();
        }
        System.out.println("hello main thread");
    }

    /**
     * 测试countDownLatch的api； {@link CountDownLatch#countDown()} 和 {@link CountDownLatch#await()} {@link CyclicBarrier}
     * 原理: 使用AQS {@link AbstractQueuedSynchronizer} 实现
     *
     * @author YU
     */
    private static void testCountDownLatch() {
        // 用完一次以后就不能重置后重新使用；目前测试的数量是大于进程数的
        final CountDownLatch downLatch = new CountDownLatch(3);

        // 子线程Runnable
        Runnable childRunnable = () -> {
            String currentThreadName = Thread.currentThread().getName();
            System.out.println(currentThreadName + "开始执行");
            int i = new Random().nextInt(5000);
            try {
                System.out.println("睡眠时间" + (3000 + i));
                Thread.sleep(3000 + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(currentThreadName + "执行完毕");
            downLatch.countDown();
        };

        // 主线程
        Runnable mainRunnable = () -> {
            long start = System.currentTimeMillis();
            Thread thread = new Thread(childRunnable, "child-thread-0001");
            Thread thread1 = new Thread(childRunnable, "child-thread-0002");
            thread.start();
            thread1.start();

            System.out.println(Thread.currentThread() + "开始执行");
            downLatch.countDown();
            try {
                boolean await = downLatch.await(1, TimeUnit.MINUTES);
                System.out.println(await);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "主线程执行完毕");
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        };


        Thread mainThread = new Thread(mainRunnable, "MainThread");
        mainThread.start();

    }


    private static void testLockState(){
        ReentrantLock reentrantLock = new ReentrantLock(true);

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 1000, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10000),
                new ThreadFactoryBuilder().setNameFormat("Thread-volatile-%d").build());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                try{
                    System.out.println("睡眠300000ms");
                    Thread.sleep(3000000);
                }catch (InterruptedException interruptedException){
                    throw new RuntimeException("运行出错");
                }finally {
                    reentrantLock.unlock();
                }
                reentrantLock.unlock();
            }
        };
        for (int i = 0; i < 100; i++) {
            threadPool.submit(runnable);
        }
    }



}
