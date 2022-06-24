package com.yu.events.eventbase.eventInterface;

/**
 * Interface for handling events of type T
 * 事件处理器接口
 *
 * @param <T> 事件类型的类型
 * @param <E> parameterized event of type T 事件的类型
 * @author Yu
 */
@FunctionalInterface
public interface EventHandler<T extends Enum<T>, E extends Event<T>> {

    /**
     * 处理函数
     *
     * @param event 事件  {@link E}
     * @author YU
     */
    void handle(E event);
}
