package com.yu.memorybarriers;

/**
 * 内存操作线程
 *
 * @author YU
 * @date 2022-05-12 15:53
 */
public class MemoryOperationThread {

    /**
     * 默认是false
     */
    volatile boolean  intentFirst;
    volatile boolean intentSecond;
    /**
     * 默认是 0
     */
    int turn = 0;

    Runnable runnableTask01 = () -> {
        intentFirst = true;
        while (intentSecond) {  // volatile read
            if (turn != 0) { // volatile read
                intentFirst = false;
                while (turn != 0) {
                }
                intentFirst = true;
            }

        }

        System.out.printf("1before: group:%s,turn:%d;intentFirst:%b;intentSecond:%b\n", Thread.currentThread().getName(), turn, intentFirst, intentSecond);
        turn = 1;      // volatile write
        intentFirst = false; // volatile write
        System.out.printf("1after: group:%s,turn:%d;intentFirst:%b;intentSecond:%b\n", Thread.currentThread().getName(), turn, intentFirst, intentSecond);

    };

    Runnable runnableTask02 = () -> {

        intentSecond = true;
        while (intentFirst) {  // volatile read
            if (turn != 0) { // volatile read
                intentSecond = false;
                while (turn != 0) {
                }
                intentSecond = true;
            }

        }

        System.out.printf("2before: group:%s,turn:%d;intentFirst:%b;intentSecond:%b\n", Thread.currentThread().getName(), turn, intentFirst, intentSecond);
        turn = 1;      // volatile write
        intentSecond = false; // volatile write
        System.out.printf("2after: group:%s,turn:%d;intentFirst:%b;intentSecond:%b\n", Thread.currentThread().getName(), turn, intentFirst, intentSecond);

    };

    Runnable runnableTask03 = () -> {

        intentFirst = true;
        while (intentSecond) {  // volatile read
            if (turn != 0) { // volatile read
                intentFirst = false;
                while (turn != 0) {
                }
                intentFirst = true;
            }

        }

        System.out.printf("before: group:%s,turn:%d;intentFirst:%b;intentSecond:%b\n", Thread.currentThread().getName(), turn, intentFirst, intentSecond);
        turn = 1;      // volatile write
        intentFirst = false; // volatile write
        System.out.printf("after: group:%s,turn:%d;intentFirst:%b;intentSecond:%b\n", Thread.currentThread().getName(), turn, intentFirst, intentSecond);

        intentSecond = true;
        while (intentFirst) {  // volatile read
            if (turn != 0) { // volatile read
                intentSecond = false;
                while (turn != 0) {
                }
                intentSecond = true;
            }

        }

        System.out.printf("before: group:%s,turn:%d;intentFirst:%b;intentSecond:%b\n", Thread.currentThread().getName(), turn, intentFirst, intentSecond);
        turn = 1;      // volatile write
        intentSecond = false; // volatile write
        System.out.printf("after: group:%s,turn:%d;intentFirst:%b;intentSecond:%b\n", Thread.currentThread().getName(), turn, intentFirst, intentSecond);

    };

}
