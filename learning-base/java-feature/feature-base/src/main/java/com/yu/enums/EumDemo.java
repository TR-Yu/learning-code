package com.yu.enums;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 测试枚举类, 对枚举内的对象理解更加深刻
 *
 * @author YU
 * @date 2022-06-14 12:11
 */
public class EumDemo {
    public static void main(String[] args) {

        Callable<String> task = () -> {
            System.out.println("call the method");
            TimeUnit.SECONDS.sleep(60);
            return "success";
        };

        Callable<String> task1 = () -> {
            TimeUnit.SECONDS.sleep(5);
            int i = 1 / 0;
            System.out.println("call the method");
            return "success";
        };

        Callable<String> task2 = () -> {
            TimeUnit.SECONDS.sleep(5);
            Thread.currentThread().interrupt();
            System.out.println("设置了中断标识为" + Thread.currentThread().isInterrupted());
            return "success";
        };

        List<String> strings = NumEnum.TWO.execAndWaitResult(task, task1, task2);
        strings.forEach(System.out::println);
        NumEnum.TWO.threadedPoolShutDown();
    }
}