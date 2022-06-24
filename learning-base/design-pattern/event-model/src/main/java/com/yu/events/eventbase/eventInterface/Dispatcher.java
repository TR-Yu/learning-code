package com.yu.events.eventbase.eventInterface;

/**
 * 分发器-注册中心
 *
 * @author YU
 * @date 2022-05-28 13:53
 */
public interface Dispatcher {

    /**
     * 事情分发器，用于分发
     *
     * @param event {@link E}
     * @author YU
     */
    <T extends Enum<T>, E extends Event<T>> void dispatchEvent(E event);

    /**
     * 事件处理器，根据事件的类型
     *
     * @param eventType    {@link T}
     * @param eventHandler {@link EventHandler}
     * @author YU
     */
    <T extends Enum<T>, E extends Event<T>> void register(T eventType, EventHandler<T, E> eventHandler);

    /**
     * 一种事件类型的类型，注册一个事件处理器，
     *
     * @param eventType    {@link Class<T>}
     * @param eventHandler {@link EventHandler}
     * @author YU
     */
    <T extends Enum<T>, E extends Event<T>> void register(Class<T> eventType, EventHandler<T, E> eventHandler);
}
