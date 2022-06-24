package com.yu.flywieght;

/**
 * @author YU
 * @date 2022-05-04 22:09
 */
public class Tree {
    private final int x;
    private final int y;
    private final TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw(String canvas) {
        type.draw(canvas,x,y);
    }

}
