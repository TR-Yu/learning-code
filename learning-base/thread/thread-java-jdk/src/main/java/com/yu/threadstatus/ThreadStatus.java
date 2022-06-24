package com.yu.thread;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程状态Demo
 *
 * @author YU
 * @date 2022-05-06 8:30
 */
public class ThreadStatus {

    public void showStatus() {
        Long mainThreadWaitTime = 200L;
        Long threadWaitTime = 3000L;
        ArrayList<Thread> threads = new ArrayList<>();
        // 总是空等待任务
        Runnable runnerTask01 = ThreadStatus::commonResource;
        // 快速完成任务（空任务）
        Runnable runnerTask02 = () -> {
        };
        // 线程睡眠
        Runnable runnerTask03 = () -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable runnerTask04 = () -> {
            ReentrantLock reentrantLock = new ReentrantLock();
            reentrantLock.lock();
            try {
                while (true) {

                }
            } finally {
                reentrantLock.unlock();
            }
        };

        Runnable runnerTask05 = () -> { int i = 1/0; return;};

        // 创建进入到同一个代码块中的两个线程： - running status and blocked status
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(runnerTask01);
            threads.add(thread);
            thread.start();
        }

        // 创建但不运行，- new status
        Thread thread2 = new Thread(runnerTask02);
        threads.add(thread2);

        // 创建快速结束任务 - terminated status
        Thread thread3 = new Thread(runnerTask02);
        thread3.start();
        threads.add(thread3);

        // running -> timed_waiting 状态
        Thread thread4 = new Thread(() -> {
            try {
                Thread.sleep(threadWaitTime);
                System.out.println("awake, I am running");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        });
        thread4.start();
        threads.add(thread4);

        Thread thread5 = new Thread(() -> {
            Thread thread6 = new Thread(runnerTask03);
            thread6.start();
            try {
                thread6.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threads.add(thread6);
        });

        thread5.start();
        threads.add(thread5);

        // 测试 重入锁对线程的状态的影响
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(runnerTask04);
            thread.start();
            threads.add(thread);
        }

        // 测试异常状态下的线程退出的影响
        Thread thread09 = new Thread(runnerTask05);
        thread09.start();
        threads.add(thread09);

        // 主线程休眠时间
        try {
            Thread.sleep(mainThreadWaitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threads.size());
        for (Thread thread : threads) {
            System.out.println(thread.getName() + thread.getState());
        }
        /*System.exit(0);*/
    }

    /**
     * @author YU
     */
    public static synchronized void commonResource() {
        while (true) {

        }
    }

}
