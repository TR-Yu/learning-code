package com.yu.flywieght;

/**
 * 树的类型
 *
 * @author YU
 * @date 2022-05-04 22:06
 */
public class TreeType {
    private final String name;
    private final String color;
    private final String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw(String canvas, int x, int y) {
        System.out.printf("canvas:%s,x:%d,y:%d%n",canvas,x,y);
    }
}
