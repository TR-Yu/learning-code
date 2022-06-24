package com.yu.builder.builder;

import com.yu.builder.entity.Car;

/**
 * 商务系列用车创建者
 *
 * @author YU
 * @date 2022-04-30 21:15
 */
public class BusinessCarBuilder<T extends BaseCarRequest> extends BaseCarBuilder implements CarBuilderInterface {
    private final Car car;
    private T carRequest;

    public BusinessCarBuilder() {
        this.car = new Car();
    }

    @Override
    public void reset() {
        System.out.println("prepare the business car's component");
    }

    @Override
    public void setSeats() {
        System.out.println("start install the seats");
        this.car.setSeatNumber(this.carRequest.getSeatNumber());
        System.out.println("end install the seats");
    }

    @Override
    public void setEngines() {
        System.out.println("start install quad driver module");
        this.car.setEngine(this.carRequest.getSize());
        System.out.println("end install quad driver module");
    }

    @Override
    public void setTripComputer() {
        System.out.println("os of trip");
    }

    @Override
    public void setGPS() {
        System.out.println("install the GPS");
    }

    @Override
    public Car getCar() {
        return this.car;
    }

    public void printADLogo() {
        System.out.println("plain the AD on the body");
    }
}
