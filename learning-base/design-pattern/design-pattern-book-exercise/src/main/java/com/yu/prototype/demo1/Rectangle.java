package com.yu.prototype.demo1;

import java.io.Serializable;

/**
 * @author YU
 * @date 2022-05-01 0:24
 */
public class Rectangle extends BaseShape implements Cloneable, Serializable {
    private double width;
    private double height;

    public Rectangle(double x, double y, String color, double width, double height) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public Rectangle clone() {
        return (Rectangle) super.clone();
    }
}
