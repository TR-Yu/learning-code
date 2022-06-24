package com.yu.events.rmi.rmibase;

import com.yu.events.eventbase.eventInterface.Event;

import java.rmi.Remote;

/**
 * rmi远程调用接口
 *
 * @author YU
 * @date 2022-05-28 16:57
 */
public interface RmiInterface extends Remote {
    /**
     * 发布HI事件
     *
     * @author YU
     * @param message  {@link String}
     */
    public void pushHiEvent(String message);
}
