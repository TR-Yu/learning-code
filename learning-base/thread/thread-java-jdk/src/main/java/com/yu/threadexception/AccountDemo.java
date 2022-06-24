package com.yu.threadexception;

/**
 * 线程转账示例
 *
 * @author YU
 * @date 2022-06-04 17:49
 */
public class AccountDemo {
    public static void main(String[] args) {

        Account A = new Account(200);
        Account B = new Account(200);
        Account C = new Account(200);
        threadSync2(A, B, C);
        System.out.println(A.getBalance());
        System.out.println(B.getBalance());
        System.out.println(C.getBalance());
    }

    /**
     * 线程内同步转账方法
     *
     * @param accounts
     */
    private static void threadSync1(Account... accounts) {

        accounts[0].transfer(accounts[1], 100);
        accounts[1].transfer(accounts[2], 120);
        accounts[2].transfer(accounts[0], 10);
    }

    /**
     * 不同线程直接协作完成 使用了同一个锁对象，完成了对临界区的控制，达到三性
     * synchronized 是如何完成原子性，有序性，可见性
     *
     * @param accounts
     */
    private static void threadSync2(Account... accounts) {
        for (int i = 0; i < accounts.length; i++) {
            int j = (i + 1) % accounts.length;
            int total = (i * 10) + 80;
            Runnable runnable = runTask(total, accounts[i], accounts[j]);
            Thread thread = new Thread(runnable, "Thread-j");
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static Runnable runTask(int total, Account... accounts) {
        return () -> accounts[0].transfer(accounts[1], total);
    }

}
