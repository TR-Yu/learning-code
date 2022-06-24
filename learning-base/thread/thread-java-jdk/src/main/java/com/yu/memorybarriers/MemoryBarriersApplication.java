package com.yu.memorybarriers;

import java.util.ArrayList;

/**
 * 内存屏障测试
 *
 * @author YU
 * @date 2022-05-12 15:53
 */
public class MemoryBarriersApplication {
    public static void main(String[] args) throws InterruptedException {
        MemoryOperationThread operationThread = new MemoryOperationThread();
        ArrayList<Thread> threads = new ArrayList<>();

/*        Thread threadSingle = new Thread(operationThread.runnableTask03);
        threadSingle.start();
        Thread.sleep(100000L);
        System.exit(0);*/

        for (int i = 0; i < 10; i++) {
            Thread thread0 = new Thread(operationThread.runnableTask01);
            thread0.setName("first ");
            Thread thread1 = new Thread(operationThread.runnableTask02);
            thread1.setName("second");
            threads.add(thread0);
            threads.add(thread1);
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
