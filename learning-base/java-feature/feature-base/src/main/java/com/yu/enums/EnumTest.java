package com.yu.enums;

import com.yu.NumEnum;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试枚举
 *
 * @author YU
 * @date 2022-06-14 12:35
 */
public class EnumTest {

    /**
     * 业务线程池
     */
    private static final ConcurrentHashMap<NumEnum,ExecutorService> threadMap = new ConcurrentHashMap<>();

    /**
     * CPU核数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    /**
     * 对外提供的获取ExecutorService接口
     *
     * @author YU
     * @return   {@link ExecutorService}
     */
    public static ExecutorService getExecutor(NumEnum threadEnum) {
        ExecutorService executorService = threadMap.computeIfAbsent(threadEnum, EnumTest::createThreadPool);
        return executorService;
    }

    private static ExecutorService createThreadPool(NumEnum threadEnum){
        int value = threadEnum.getValue();
        return Executors.newFixedThreadPool(value);
    }
}
