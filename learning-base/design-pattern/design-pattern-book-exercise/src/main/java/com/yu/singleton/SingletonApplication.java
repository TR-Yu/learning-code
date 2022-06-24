package com.yu.singleton;

import com.yu.singleton.demo1.SingletonExample;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


/**
 * 单例模式: 只产生一个对象；对构造函数进行私有化，只能由一个方法产生
 *
 * @author YU
 * @date 2022-05-03 17:24
 */
public class SingletonApplication {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(SingletonApplication.class.getSimpleName());

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1000000));
        Runnable runnable = () -> {
            SingletonExample singleInstance = SingletonExample.getSingleInstance();
            System.out.println(singleInstance);
        };

        for (int i = 0; i < 1000; i++) {
            poolExecutor.submit(runnable);
        }
        poolExecutor.shutdown();
        String str = "82";
        logger.info(() -> "返回debug信息" + str);
    }
}
