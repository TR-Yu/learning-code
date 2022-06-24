package com.yu.countdownlatch;

/**
 * 设置两组线程：
 * 一组线程为司机角色；一组线程为工人角色
 * 两种角色之前存在关系的：工人角色在司机角色没有完全实现初始化的时候不能work;
 * 在工人没有完全完成work()钱，司机不能发动车
 * 设计部分：使用 CountDownLatch
 * worker 等待 DriverInitSingle
 * Driver 等待 WorkerCompleteSingle
 * 1. WorkTask  DriverTask 类来说明工作
 * 2. 入参总的工人数和司机数，将线程池的初始过程包装起来的一个类
 * @author YU
 * @date 2022-06-09 9:09
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        SendService sendServiceGroup1 = new SendService(2, 3);
        sendServiceGroup1.logicalSend();

        SendService sendServiceGroup2 = new SendService(1, 2);
        sendServiceGroup2.logicalSend();

    }
}
