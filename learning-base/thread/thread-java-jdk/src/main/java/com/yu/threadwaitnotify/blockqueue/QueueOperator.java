package com.yu.threadwaitnotify.blockqueue;

import cn.hutool.core.lang.Assert;

import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 消费者
 *
 * @author YU
 * @date 2022-06-06 16:38
 */
public class QueueOperator {
    /**
     * 操作的队列对象
     */
    private final Queue queue;

    private ThreadPoolExecutor threadPool;

    /**
     * 生产的最大的消息数，用于达到最大值后，自动阻塞
     */
    private int maxSize;

    public QueueOperator(Queue queue, int maxSize, ThreadPoolExecutor threadPool) {
        this.queue = queue;
        this.threadPool = threadPool;
        this.maxSize = maxSize;
    }

    public void produceProducts(Action action, int productCount) {
        Assert.isTrue(Objects.nonNull(queue), "入参不合法");
        Runnable producerTask = () -> {
            synchronized (this.queue) {
                while (this.queue.getCurrentCapacity() >= this.queue.getCapacity()){
                    try {
                        System.out.println(Thread.currentThread().getName() + "生产者等待");
                        this.queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while (this.queue.getCurrentCapacity() < this.queue.getCapacity() ) {
                    action.operateAction(this.queue);
                    System.out.println(Thread.currentThread().getName() + "生产者通知其它线程");
                    this.queue.notifyAll();
                    try {
                        this.queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        for (int i = 0; i < productCount; i++) {
            this.threadPool.submit(producerTask);
        }
    }

    public void consumeProducts(Action action, int consumerCount) {
        Assert.isTrue(Objects.nonNull(queue), "入参不合法");
        Runnable consumerTask = () -> {
            synchronized (this.queue) {
                while (this.queue.getCurrentCapacity() != this.queue.getCapacity()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "消费者等待");
                        this.queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while (this.queue.getCurrentCapacity() == this.queue.getCapacity()) {
                    action.operateAction(this.queue);
                    //唤醒其它的阻塞线程
                    System.out.println(Thread.currentThread().getName() + "唤醒其它线程");
                    this.queue.notifyAll();
                    try {
                        this.queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        for (int i = 0; i < consumerCount; i++) {
            this.threadPool.submit(consumerTask);
        }
    }
}
