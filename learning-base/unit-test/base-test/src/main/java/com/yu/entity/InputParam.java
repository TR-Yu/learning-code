package com.yu.entity;

import java.math.BigDecimal;

/**
 * @author YU
 * @date 2022-06-17 21:51
 */
public class InputParam {
    private Integer id ;
    private BigDecimal price;
    private String description;

    public InputParam(Integer id, BigDecimal price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
