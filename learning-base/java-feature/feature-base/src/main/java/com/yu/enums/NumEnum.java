package com.yu.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author YU
 * @date 2022-06-14 13:33
 */
public enum NumEnum {
    /**
     *
     */
    ONE(1, "第一个"),
    /**
     *
     */
    TWO(2, "第二"),
    /**
     *
     */
    THREE(3, "第三个");

    /**
     *
     */
    private int value;

    /**
     *
     */
    private String description;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    NumEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public void printValue() {
        System.out.println(this);
    }

    /**
     *
     *
     * @author YU
     * @param tasks  {@link Callable<T>...}
     * @return result   {@link List<T>}
     */
    public <T> List<T> execAndWaitAll(Callable<T>... tasks) {
        ExecutorCompletionService<T> completionService = new ExecutorCompletionService<>(Executors.newFixedThreadPool(3));
        int n = tasks.length;
        ArrayList<T> result = new ArrayList<>(n);
        ArrayList<Future<T>> futures = new ArrayList<>(n);
        try {
            for (Callable<T> task : tasks) {
                futures.add(completionService.submit(task));
                for (int i = 0; i < n; i++) {
                    try {
                        T t = completionService.take().get();
                        result.add(t);
                    } catch (Exception e) {
                        System.out.println("执行中有一个线程任务抛出异常中断");
                        break;
                    }
                }
            }
        } finally {
            for (Future<T> future : futures) {
                future.cancel(true);
            }
        }
        return result;
    }

    /**
     * 执行一批任务串行执行，因为使用了get()
     */
    public <T> List<T> execAndWaitResult(Callable<T>... tasks){
        List<Future<T>> resultFutures = new ArrayList<>();
        for (Callable<T> task : tasks) {
            ExecutorService executor = EnumTest.getExecutor(this);
            System.out.println(executor);
            Future<T> submit = executor.submit(task);
            resultFutures.add(submit);
        }

        // 等待所有任务的结果
        List<T> results = new ArrayList<>();
        resultFutures.forEach(r -> {
            try {
                results.add(r.get());
            } catch (CancellationException cancellationException) {
                System.out.println("线程任务取消");
            } catch (Exception e) {
                System.out.println("任务异常未知中断");
            }
        });
        return results;
    }

    public void threadedPoolShutDown(){
        EnumTest.getExecutor(this).shutdown();
    }
}
