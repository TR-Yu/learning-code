package com.yu.forkjoin;


import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * ForkJoin Framework Demo
 * {@link ForkJoinPool}
 * <p>
 * {@link ForkJoinTask} ---> {@link RecursiveAction} {@link  RecursiveTask}
 *
 * @author YU
 * @date 2022-06-11 13:02
 */
public class ForkJoinDemo {

    private final static Logger logger = Logger.getLogger("ForkJoinDemo");
    public static void main(String[] args) {
        int n = 40;

        //设置子线程名称， 重写了 ForkJoinPool#ForkJoinWorkerThreadFactory的方法
        ForkJoinPool.ForkJoinWorkerThreadFactory factory = pool -> {
            ForkJoinWorkerThread workerThread = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
            workerThread.setName("my-thread" + workerThread.getPoolIndex());
            return workerThread;
        };

        ForkJoinPool joinPool = new ForkJoinPool(1, factory, null, false);

        Fibonacci fibonacci = new Fibonacci(n);
        Integer invoke = joinPool.invoke(fibonacci);
        logger.info(String.format("Fibonacci %s 的结果是 %s",n,invoke));
    }

    static class Fibonacci extends RecursiveTask<Integer> {
        final int n;
        Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            // 子问题最小粒度
            if (n <= 1) {
                return n;
            }

            Fibonacci f1 = new Fibonacci(n - 1);
            // 拆分子任务
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            return  f2.compute() + f1.join();
        }
    }
}
