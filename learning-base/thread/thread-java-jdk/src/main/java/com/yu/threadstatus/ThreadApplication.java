package com.yu.thread;



import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * 线程运行状态
 *
 * @author YU
 * @date 2022-05-06 8:16
 */
public class ThreadApplication {
    private static Logger logger = Logger.getLogger("ThreadApplication");
    public static void main(String[] args) throws ExecutionException, InterruptedException {
    /*    // 创建线程并打印出状态;
        ThreadStatus threadStatus = new ThreadStatus();
        threadStatus.showStatus();
        new ThreadApplication().put(1);
        System.exit(0);*/
        Runnable runnable = () -> {
            System.out.println("doing this work");
        };
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        FutureTask<String> futureTask = new FutureTask<>(new T1Task());
        FutureTask<String> futureTask2 = new FutureTask<>(new T2Task());
        executorService.submit(futureTask);
        executorService.submit(futureTask);
        executorService.submit(futureTask);
        executorService.submit(futureTask);
        executorService.submit(futureTask2);
        executorService.submit(futureTask2);

        executorService.execute(runnable);
        System.out.println("status:" + futureTask.isDone());
        futureTask2.get();
        /*System.out.println("message: " + s);*/
        /* String s = futureTask.get();*/
        /*String s1 = futureTask2.get();*/
        executorService.shutdown();
        futureTask.get();

        /*  logger.info(futureTask.get()+futureTask2.get());*/
        logger.info("开始泡茶");
    }

    static class T1Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            logger.info("T1:洗水壶");
            TimeUnit.SECONDS.sleep(1);
            logger.info("T1: 开始烧水");
            TimeUnit.SECONDS.sleep(1);
            throw new IllegalArgumentException();
        }
    }

    static class T2Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            logger.info("T2:开始洗茶壶");
            TimeUnit.SECONDS.sleep(1);
            logger.info("T2:洗茶杯");
            TimeUnit.SECONDS.sleep(2);
            logger.info("T2:拿茶叶");
            TimeUnit.SECONDS.sleep(1);
            return "T2:福鼎白茶拿到了";
        }
    }

    public <T extends String, V extends Number> void put(V number) {
        Callable<T> callable = () -> (T) String.valueOf(number);
        RunnableFuture<T> futureTask = new FutureTask(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        T t = null;
        try {
             t = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
        }

        System.out.println(t);

    }

    public void testDeadLock() {
        //L1、L2阶段共用的线程池
        ExecutorService es = Executors.newFixedThreadPool(2);
        //L1阶段的闭锁
        CountDownLatch l1=new CountDownLatch(2);
        System.out.println("L1");
        //执行L1阶段任务
        es.execute(() -> {
            //L2阶段的闭锁
            CountDownLatch l2 = new CountDownLatch(2);
            //执行L2阶段子任务
            for (int j = 0; j < 2; j++) {
                es.execute(() -> {
                    System.out.println("L2");
                    l2.countDown();
                });
            }
            //等待L2阶段任务执行完
            try {
                l2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            l1.countDown();
        });
        System.out.println("L1");
        //执行L1阶段任务
        es.execute(() -> {
            //L2阶段的闭锁
            CountDownLatch l2 = new CountDownLatch(2);
            //执行L2阶段子任务
            for (int j = 0; j < 2; j++) {
                es.execute(() -> {
                    System.out.println("L2");
                    l2.countDown();
                });
            }
            //等待L2阶段任务执行完
            try {
                l2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            l1.countDown();
        });
        //等着L1阶段任务执行完l1.await();System.out.println("end");
    }

}
