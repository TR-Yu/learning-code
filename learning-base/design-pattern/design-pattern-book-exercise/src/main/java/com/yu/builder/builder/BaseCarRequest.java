package com.yu.builder.builder;

/**
 * 客户要求的车的基本要求
 *
 * @author YU
 * @date 2022-04-30 23:29
 */
public class BaseCarRequest {
    private String colorName;
    private String carType;
    private String size;
    private boolean manual;
    private int seatNumber;

    public BaseCarRequest(String colorName, String carType, String size, boolean manual) {
        this.colorName = colorName;
        this.carType = carType;
        this.size = size;
        this.manual = manual;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
