package com.yu.reflects.entity;

import com.yu.reflects.annotations.StringFilterEmo;

/**
 * 需要保持入库的DTOentity
 *
 * @author YU
 * @date 2022-05-26 14:14
 */
public class Entity {

    @StringFilterEmo
    private String address;

    @StringFilterEmo
    private String nickname;

    @StringFilterEmo
    private String goodsInfoName;

    public Entity() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGoodsInfoName() {
        return goodsInfoName;
    }

    public void setGoodsInfoName(String goodsInfoName) {
        this.goodsInfoName = goodsInfoName;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "address='" + address + '\'' +
                ", nickname='" + nickname + '\'' +
                ", goodsInfoName='" + goodsInfoName + '\'' +
                '}';
    }
}
