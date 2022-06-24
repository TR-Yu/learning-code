package com.yu.events.eventbase;

import com.yu.events.eventbase.eventInterface.Dispatcher;
import com.yu.events.eventbase.eventInterface.Event;

/**
 * 所有事件的父类：包含类型和时间戳
 *
 * @author YU
 * @date 2022-05-28 14:09
 */
public class AbstractEvent<T extends Enum<T>> implements Event<T> {

    private final T type;

    private final long timestamp;

    private final Dispatcher dispatcher;

    public AbstractEvent(T type, Dispatcher dispatcher) {
        this.type = type;
        this.dispatcher = dispatcher;
        this.timestamp = -1L;
    }

    public AbstractEvent(T type, long timestamp, Dispatcher dispatcher) {
        this.type = type;
        this.timestamp = timestamp;
        this.dispatcher = dispatcher;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    @Override
    public T getType() {
        return this.type;
    }

    @Override
    public long getTimestamp() {
        return 0;
    }

    @Override
    public String toString() {
        return "AbstractEvent{" +
                "type=" + type +
                '}';
    }
}
