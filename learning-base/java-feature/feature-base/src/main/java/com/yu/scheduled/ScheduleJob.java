package com.yu.scheduled;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时器
 *
 * @author YU
 * @date 2022-05-29 9:59
 */
public class ScheduleJob {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        Runnable running = () -> {
            System.out.println("todo something");
        };
        service.scheduleAtFixedRate(running,1,2, TimeUnit.SECONDS);
    }
}
