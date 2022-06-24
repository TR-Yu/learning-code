package com.yu.events.eventbase.eventInterface;

/**
 * Event 事件
 *
 * @author YU
 * @date 2022-05-28 13:50
 */
public interface Event<T extends Enum<T>> {

    /**
     * 事件类型的类型
     *
     * @return {@link T}
     * @author YU
     */
    T getType();

    /**
     * 获取时间戳
     *
     * @return {@link long}
     * @author YU
     */
    long getTimestamp();


}
