package com.yu.countdownlatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * 司机工作线程任务
 *
 * @author YU
 * @date 2022-06-09 9:23
 */
public class DriverTask implements Callable<String> {
    private final CountDownLatch driverInitSingle;
    private final CountDownLatch workerCompleteSingle;

    public DriverTask(CountDownLatch driverInitSingle, CountDownLatch workerCompleteSingle) {
        this.driverInitSingle = driverInitSingle;
        this.workerCompleteSingle = workerCompleteSingle;
    }

    @Override
    public String call() {
        driverStart();
        driverInitSingle.countDown();
        try {
            workerCompleteSingle.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverBusiness();
        return "success";
    }

    /**
     * 司机初始化工作
     */
    public void driverStart() {
        System.out.println(Thread.currentThread().getName() + "driver start init");
    }

    /**
     * 司机的工作业务逻辑1
     */
    public void driverBusiness() {
        System.out.println(Thread.currentThread().getName() + "driver had do business");
    }

}
