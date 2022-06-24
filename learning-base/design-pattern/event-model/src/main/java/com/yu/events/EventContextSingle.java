package com.yu.events;

import com.yu.events.eventbase.AsyncDispatcher;

import java.util.concurrent.Executors;

/**
 * dispatcher容器单例
 *
 * @author YU
 * @date 2022-05-28 17:15
 */
public class EventContextSingle {

    private static volatile EventContextSingle eventContextSingle;

    private AsyncDispatcher asyncDispatcher;

    private EventContextSingle() {
    }

    public static EventContextSingle getSingleEventContext() {
        if (eventContextSingle == null) {
            if (eventContextSingle == null) {
                synchronized (EventContextSingle.class) {
                    eventContextSingle = new EventContextSingle();
                    eventContextSingle.initAsyncDispatcher();
                }
            }
        }
        return eventContextSingle;
    }

    public void initAsyncDispatcher() {
        // 事件容器的注册并初始化：
        this.asyncDispatcher = new AsyncDispatcher(Executors.newSingleThreadExecutor());
    }

    public AsyncDispatcher getAsyncDispatcher() {
        return this.asyncDispatcher;
    }
}
