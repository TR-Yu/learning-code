package com.yu.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 完成发货服务类
 *
 * @author YU
 * @date 2022-06-09 9:35
 */
public class SendService {
    private final int driverCount;
    private final int workerCount;
    private final CountDownLatch driverInitSingle;
    private final CountDownLatch workerCompleteSingle;
    private static volatile ThreadPoolExecutor sendLogicalExecutor;

    public SendService(int driverCount, int workerCount) {
        this.driverCount = driverCount;
        this.workerCount = workerCount;
        this.driverInitSingle = new CountDownLatch(driverCount);
        this.workerCompleteSingle = new CountDownLatch(workerCount);
    }

    public void logicalSend() {
        sendLogicalExecutor = getSingletonPoolExecutor();
        ArrayList<Callable<String>> list = new ArrayList<>();
        System.out.println(sendLogicalExecutor);
        for (int i = 0; i < driverCount; i++) {
            DriverTask driverTask = new DriverTask(driverInitSingle, workerCompleteSingle);
            list.add(driverTask);
        }

        for (int i = 0; i < workerCount; i++) {
            WorkTask workTask = new WorkTask(driverInitSingle, workerCompleteSingle);
            list.add(workTask);
        }

        try {
            List<Future<String>> futures = sendLogicalExecutor.invokeAll(list);
            futures.forEach(el -> {
                try {
                    System.out.println(el.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 问题1： 为什么需要核心线程数为 driverCount + workerCount 的时候，才可以正常使用,
    // 要是核心数少于需要运行的线程数，因为其它的线程会被先放到任务工作队列中等待而等待空闲线程操作，
    // 而线程已满的情况下，没有多余的线程而且导致线程的相互等待
    // 而未执行的线程则在等待执行的任务完成释放cpu，执行中的等待排队中的任务执行
    // 问题2： 涉及到的线程是如何回收的？
    // 线程池需要封装为静态方法只能在new新对象的时候只初始化一个
    private static ThreadPoolExecutor getSingletonPoolExecutor(){
        if (sendLogicalExecutor == null) {
            synchronized (SendService.class) {
                if(sendLogicalExecutor == null){
                   return new ThreadPoolExecutor(
                            8, 10, 20, TimeUnit.SECONDS, new ArrayBlockingQueue(1));
                }
            }
        }
        return sendLogicalExecutor;
    }

}
