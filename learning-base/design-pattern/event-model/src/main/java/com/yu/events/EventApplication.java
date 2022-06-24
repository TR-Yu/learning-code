package com.yu.events;

import com.yu.events.eventbase.AsyncDispatcher;
import com.yu.events.rmi.rmibase.RmiImplement;
import com.yu.events.rmi.rmibase.RmiInterface;

import java.io.*;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Logger;

/**
 * 事件模型
 *
 * @author YU
 * @date 2022-05-28 13:43
 */

public class EventApplication {
    private final static Logger LOGGER = Logger.getLogger(EventApplication.class.getSimpleName());

    public static void main(String[] args) throws IOException {

        EventContextSingle singleEventContext = EventContextSingle.getSingleEventContext();
        AsyncDispatcher asyncDispatcher = singleEventContext.getAsyncDispatcher();

        //将事件注入到容器中
        asyncDispatcher.register(ExecEventType.HI, (ExecEvent el) -> {
            LOGGER.info("Hi " + el.getWord());
        });
        asyncDispatcher.register(ExecEventType.HELLO, (ExecEvent el) -> {
            LOGGER.info("HELLO " + el.getWord());
        });
        asyncDispatcher.register(ExecEventType.class, (ExecEvent el) -> {
            LOGGER.info("通过 class 注册 " + el.getWord());
        });
        // 容器启动并等待调用
        asyncDispatcher.serviceStart();

        ExecEvent execEvent = new ExecEvent(ExecEventType.HELLO, asyncDispatcher, "进程同步测试");
        asyncDispatcher.dispatchEvent(execEvent);

    }
}
