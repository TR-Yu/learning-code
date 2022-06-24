package com.yu.threadwaitnotify.blockqueue;

/**
 * 生产者接口
 *
 * @author YU
 * @date 2022-06-06 16:47
 */
@FunctionalInterface
public interface Action <V extends Queue>{

    /**
     * 生产方法
     */
    public void operateAction(V v);

}
