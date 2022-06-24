package com.yu.syncfinal;

import java.util.HashSet;
import java.util.Set;

/**
 * synchronized 锁，在异常时，完成时，wait()方法时都会自动释放锁，注意Lock锁不会
 *
 *
 * @author YU
 * @date 2022-06-03 22:26
 */
public class FinalDemo {
    int counter = 10;
    int line = 9;
    volatile boolean flag;

    public static void main(String[] args) {

        FinalDemo finalDemo = new FinalDemo();

        Runnable addTask = new Runnable() {
            @Override
            public void run() {
                {
                    synchronized (finalDemo) {
                        finalDemo.deviceCount();
                        System.out.println("counter: " + finalDemo.counter);
                    }
                }
            }
        };

        Runnable runTask = new Runnable() {
            @Override
            public void run() {
                synchronized (finalDemo) {
                    throw new RuntimeException("异常了");
                }
            }
        };

        Runnable lineTask = new Runnable() {
            @Override
            public void run() {
                synchronized (finalDemo) {
                    finalDemo.line --;
                    System.out.println("line : " + System.nanoTime());
                    System.out.println("line : " + finalDemo.line);
                }
            }
        };

        Runnable orderTask = () -> {
            System.out.println(Thread.currentThread().getName());
            finalDemo.writeValue(1,1);
            finalDemo.readValue();
        };
        Set<Thread> threads = new HashSet<>(100);
        for (int i = finalDemo.counter; i > 0; i--) {
            /*Thread thread = new Thread(addTask);
            Thread exceptThread = new Thread(lineTask);
            threads.add(exceptThread);
            threads.add(thread);*/
            Thread thread = new Thread(orderTask);
            threads.add(thread);
        }

        threads.forEach(Thread::start);
    }

    public synchronized void deviceCount() {
        System.out.println("counter : " + System.nanoTime());
        counter--;
    }

    public synchronized int getCount() {
        return counter;
    }

    public synchronized void addCount() {
        System.out.println("counter : " + System.nanoTime());
        counter++;
    }


    public void writeValue(int counter, int line){

        this.counter = counter;
        this.line = line;
        //volatile 写
        flag = true;
    }

    public void readValue(){
        // volatile 读
        boolean a = flag;
        System.out.println(this.counter);
        System.out.println(this.line);
    }

}
