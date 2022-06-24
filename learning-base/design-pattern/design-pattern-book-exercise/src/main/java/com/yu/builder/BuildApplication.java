package com.yu.builder;

import com.yu.builder.builder.BaseCarRequest;
import com.yu.builder.builder.SuvCarBuilder;
import com.yu.builder.director.CarDirector;
import com.yu.builder.entity.Car;

/**
 * 创建者模式：构建复杂对象时的运用，特征是：对 将生成过程，切分： Builder , Director, client；
 * 目的是将 Builder 和 client 之间解耦，将流程编排顺序放在 Director； 面向接口编程
 * 目前的理解是：Builder模式，主要是将生成的步骤单元化，然后将生成者的步骤关系解耦，同时对步骤本身进行方法级别的封装，
 *
 * @author YU
 * @date 2022-04-30 20:46
 */
public class BuildApplication {

    /**
     * @author YU
     */
    public static void main(String[] args) {
        BaseCarRequest baseCarRequest = new BaseCarRequest("red", "suv", "small", false);
        SuvCarBuilder<BaseCarRequest> suvCarBuilder = new SuvCarBuilder<>(baseCarRequest);
        CarDirector.createCar(suvCarBuilder);
        Car car = suvCarBuilder.getCar();
        System.out.println();
    }
}
