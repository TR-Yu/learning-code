package com.yu.flywieght;

/**
 * 享元模式：将需要重用的对象，在过程中复用，这是一种优化手段
 * 具体的实现过程：将不变的引用部分抽取；不变的部分因为是多个地方引用，所以必须是不可变的；对于对象本身的生成可以采用工厂模式获取；
 * 用容器进行存储和管理
 *
 * @author YU
 * @date 2022-05-04 18:38
 */
public class FlyweightApplication {
    public static void main(String[] args) {
        Forest forest = new Forest();
        forest.plantTree(1,2,"白桦树","绿色","很好看的树");
        forest.plantTree(13,24,"白桦树","绿色","很好看的树");

        forest.draw("美好的森林景色");
    }
}
