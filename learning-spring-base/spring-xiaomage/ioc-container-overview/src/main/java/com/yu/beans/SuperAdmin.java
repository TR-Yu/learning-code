package com.yu.beans;

import com.yu.beans.annotion.Super;

import java.util.StringJoiner;

/**
 * @author YU
 * @date 2022-06-01 18:00
 */
@Super
public class SuperAdmin extends Admin{
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SuperAdmin.class.getSimpleName() + "{", "}")
                .add("address='" + address + "'")
                .add("id='" + super.getId() + "'")
                .add("name='" + super.getName() + "'")
                .toString();
    }
}
