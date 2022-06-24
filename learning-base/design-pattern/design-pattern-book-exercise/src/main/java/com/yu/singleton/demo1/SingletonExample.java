package com.yu.singleton.demo1;

/**
 * 单例类 : 非线程安全的
 *
 * @author YU
 * @date 2022-05-03 17:33
 */
public class SingletonExample {

    private static volatile SingletonExample singleton;

    private SingletonExample() {
    }

    @SuppressWarnings({"all"})
    public static synchronized SingletonExample getSingleInstance() {

        // 创建新的对象才是最重要的代码段而获取则不需要放入到并发中
        if (singleton == null) {
            singleton = new SingletonExample();
            return singleton;
        }
        return singleton;

    }

    /**
     * 双锁检测机制
     *
     * 优化方案将同步代码块控制到最小化
     * 采用双检测的目的是避免进入到锁内，在外层先进行一个判断（针对后续对象已经生成）
     * 锁内部的代码块是为了在为null时的创建新对象
     * volatile 防止指令重排 @see JMM
     *
     * @return {@link SingletonExample}
     * @author YU
     */
    public static SingletonExample getSingletonTwo() {
        if (singleton == null) {
            synchronized (SingletonExample.class) {
                if (singleton == null) {
                    singleton = new SingletonExample();
                    return singleton;
                }
            }
        }
        return singleton;
    }
}
