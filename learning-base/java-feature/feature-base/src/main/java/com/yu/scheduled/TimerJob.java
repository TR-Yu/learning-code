package com.yu.scheduled;

import java.util.Timer;
import java.util.TimerTask;

/**
 * java的定时任务使用
 *
 * @author YU
 * @date 2022-05-28 21:11
 */
public class TimerJob {
    public static void main(String[] args) {
        Timer running = new Timer("running", true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("test timer");
            }
        };

        running.schedule(timerTask,2000,2000);

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
