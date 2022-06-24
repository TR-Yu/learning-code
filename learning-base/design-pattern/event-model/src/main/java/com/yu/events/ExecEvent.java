package com.yu.events;

import com.yu.events.eventbase.AbstractEvent;
import com.yu.events.eventbase.eventInterface.Dispatcher;

/**
 * 事件
 *
 * @author YU
 * @date 2022-05-28 15:36
 */
public class ExecEvent extends AbstractEvent<ExecEventType> {

    private final String word;

    public ExecEvent(ExecEventType type, Dispatcher dispatcher, String word) {
        super(type, dispatcher);
        this.word = word;
    }

    public ExecEvent(ExecEventType type, long timestamp, Dispatcher dispatcher, String word) {
        super(type, timestamp, dispatcher);
        this.word = word;
    }

    public String getWord() {
        return this.word;
    }
}
