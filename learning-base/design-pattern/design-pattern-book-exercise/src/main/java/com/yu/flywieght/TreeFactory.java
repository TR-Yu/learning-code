package com.yu.flywieght;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 树的工厂类
 *
 * @author YU
 * @date 2022-05-04 22:12
 */
public class TreeFactory {

    private static final ConcurrentHashMap<String, TreeType> treeMap = new ConcurrentHashMap<>();
    public static TreeType getTreeType(String name, String color, String texture) {

        String mapKey = createMapKey(name, color, texture);
        TreeType typeValue = new TreeType(name, color, texture);
        return treeMap.computeIfAbsent(mapKey, k -> typeValue);

    }

    private static String createMapKey(String name, String color, String texture) {
        return String.format("%s%s%s",name,color,texture);
    }
}
