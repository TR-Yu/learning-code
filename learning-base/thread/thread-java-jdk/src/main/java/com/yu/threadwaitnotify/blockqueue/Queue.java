package com.yu.threadwaitnotify.blockqueue;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * 阻塞队列
 *
 * @author YU
 * @date 2022-06-06 16:18
 */
public class Queue {

    /**
     * 队列实例
     */
    private List<String> entryList;

    /**
     * 队列容量
     */
    private int capacity;

    /**
     * 初始化队列
     */
    public Queue(int capacity) {

        Assert.isTrue(capacity > 0, "capacity is litter than 0");
        this.entryList = new LinkedList<>();
        this.capacity = capacity;

    }

    public void addEntryEnd(String message) {
        Assert.notNull(message);
        Assert.isTrue(this.entryList != null, "Queue not init");
        this.entryList.add(message);
    }

    public String getPushEntryFifo() {
        Assert.isTrue(!CollectionUtil.isEmpty(entryList), "Queue is Empty");
        return this.entryList.remove(0);
    }

    public int getCurrentCapacity() {
        return this.entryList.size();
    }

    public int getCapacity() {
        return this.capacity;
    }
}
