package com.yu.builder.director;

import com.yu.builder.builder.CarBuilderInterface;

/**
 * 用于车辆的步骤编排者 就是一个 Utils
 *
 * @author YU
 * @date 2022-04-30 21:09
 */
public class CarDirector {
    private CarBuilderInterface carBuilder;
    public static void createCar(CarBuilderInterface carBuilder){
        carBuilder.reset();
        carBuilder.setEngines();
        carBuilder.setGPS();
    }
}
