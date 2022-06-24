package com.yu.threadwaitnotify;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.yu.threadwaitnotify.blockqueue.Queue;
import com.yu.threadwaitnotify.blockqueue.QueueOperator;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 实现的一个阻塞的消息队列 采用 wait(), notify()实现的 阻塞队列
 * <p>
 * 临界资源是队列（FIFO) 采用LinkedList 实现；
 * 生产者一个， 消费者一个，队列一个
 * 生产者生成消息放入到队列中，然后将自己阻塞起来，唤醒消费者线程消费，然后消费完成以后，消费者将直接阻塞，唤醒生产者
 * queueOperator#produceProducts和 queueOperator#consumerProudcts
 * 注意，可以有N个消费者对N个生产者，但是因为对queue的操作是以队列为临界资源的，其实并不能提高并发。
 *
 * @author YU
 * @date 2022-06-06 16:11
 */
public class WaitNotifyDemo {

    public static void main(String[] args) {
        Queue queue = new Queue(10);
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(8, 200, 1, TimeUnit.SECONDS,
                        new LinkedBlockingDeque<>(100),
                        new ThreadFactoryBuilder().setNameFormat("operator-%d").build());
        QueueOperator queueOperator = new QueueOperator(queue, 10, threadPoolExecutor);

        queueOperator.consumeProducts((queue1 -> {
            while (queue1.getCurrentCapacity() > 0) {
                String pushEntryFifo = queue1.getPushEntryFifo();
                System.out.println(Thread.currentThread().getName() + "消费了" + pushEntryFifo);
            }
        }), 1);

        queueOperator.produceProducts((queue1 -> {
            while (queue1.getCapacity() > queue1.getCurrentCapacity()) {
                String message = Thread.currentThread().getName() + "hello,你好啊message: " + queue.getCurrentCapacity();
                queue1.addEntryEnd(message);
                System.out.println(Thread.currentThread().getName() + "生产了" + message);
            }
        }), 2);
    }
}
