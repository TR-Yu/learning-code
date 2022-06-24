package com.yu.builder.builder;

import com.yu.builder.entity.Car;

/**
 * Bulider 的接口: 示例：车的建造
 * 对于本身创建过程就是有大量方法内的居然方法是重复的则可以，在接口实现下在弄一个抽象类做基类，定义
 * @author YU
 * @date 2022-04-30 20:54
 */
public interface CarBuilderInterface {
    /**
     * 用于车的开启创建的初始化过程
     */
    void reset();

    /**
     * 安装的座椅的数量
     *
     */
    void setSeats();

    /**
     * 汽车引擎
     *
     */
    void setEngines();

    /**
     * 设置路线导航系统
     */
    void setTripComputer();

    /**
     * 设置GPS系统
     */
    void setGPS();

    /**
     * 获取创建的对象
     *
     * @return   {@link Car}
     */
    Car getCar();
}
