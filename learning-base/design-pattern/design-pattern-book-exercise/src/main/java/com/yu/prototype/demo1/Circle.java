package com.yu.prototype.demo1;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author YU
 * @date 2022-05-01 0:26
 */
public class Circle extends BaseShape implements Cloneable, Serializable {
    private BigDecimal radius;

    public Circle(double x, double y, String color, BigDecimal radius) {
        super(x, y, color);
        this.radius = radius;
    }

    public Circle(BigDecimal radius) {
        this.radius = radius;
    }

    public BigDecimal getRadius() {
        return radius;
    }

    public void setRadius(BigDecimal radius) {
        this.radius = radius;
    }


    @Override
    public Circle clone() {
        return (Circle) super.clone();
    }

}
