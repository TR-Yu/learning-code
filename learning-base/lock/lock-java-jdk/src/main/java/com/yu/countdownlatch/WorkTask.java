package com.yu.countdownlatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * 工人工作线程任务
 *
 * @author YU
 * @date 2022-06-09 9:23
 */
public class WorkTask implements Callable<String> {
    private final CountDownLatch driverInitSingle;
    private final CountDownLatch workerCompleteSingle;

    public WorkTask(CountDownLatch driverInitSingle, CountDownLatch workerCompleteSingle) {
        this.driverInitSingle = driverInitSingle;
        this.workerCompleteSingle = workerCompleteSingle;
    }

    @Override
    public String call() {
        try {
            driverInitSingle.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        workerStart();
        workerBusiness();
        workerCompleteSingle.countDown();
        return "success";
    }

    /**
     * 司机初始化工作
     */
    public void workerStart() {
        System.out.println(Thread.currentThread().getName() + "worker start init");
    }

    /**
     * 司机的工作业务逻辑1
     */
    public void workerBusiness() {
        System.out.println(Thread.currentThread().getName() + "worker had do business");
    }
}
