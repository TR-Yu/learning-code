package com.yu.interrupt;

import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 中断示例
 * 简单的中断的使用：1. 主线程内调用子线程的中断方法，在子线程内捕获这个中断（1，一直在运行中的，则处理中断） 2. 处于阻塞和等待中的直接处理中断异常
 *
 * 一组线程的中断，
 * @author YU
 * @date 2022-06-07 0:07
 */
public class InterruptDemo {
    public static void main(String[] args) {
        try {
            new InterruptDemo().SimpleInterrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void SimpleInterrupt() throws InterruptedException {

        // 子线程
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("run run running");
                //标识位是否设置为了true,是则中断运行
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "exit the thread run");
                    break;
                }
            }
        });
        thread.start();

        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                // 清除中断标识，在抛出InterruptedException后，所以需要重新设置下标识
                System.out.println(Thread.currentThread().getName() + "exit the thread run");
                Thread.currentThread().interrupt();
            }
        });

        thread1.start();


        Thread.sleep(500);
        thread.interrupt();
        Thread.sleep(500);
        thread1.interrupt();
        System.out.println(thread1.isInterrupted());

    }
}
