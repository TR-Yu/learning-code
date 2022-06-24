package com.yu.flywieght;

import java.util.ArrayList;

/**
 * 森林
 *
 * @author YU
 * @date 2022-05-04 22:43
 */
public class Forest {
    private final ArrayList<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, String color, String texture) {
        TreeType type = TreeFactory.getTreeType(name, color, texture);
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }

    public void draw(String canvas) {
        for (Tree tree : trees) {
            tree.draw(canvas);
        }
    }
}
