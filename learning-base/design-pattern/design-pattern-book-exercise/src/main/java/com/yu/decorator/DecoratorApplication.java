package com.yu.decorator;

import com.yu.decorator.processor.EncryptionDecorator;
import com.yu.decorator.processor.FileDataSource;

/**
 * 装饰者模式：对原有功能的拓展能力加强，不必动原有代码
 * 原有接口，原实现的类，采用组装原则放入到的Base类; Base类的作用是解耦，实际上，不通过它实际在client端直接使用也是可以的，但是会造成
 * 具体地实现和调用端无法解耦
 * client本身 创建拓展类，
 * 但是例子中的好像有问题就是读入和写出一直都好像有问题
 *
 * @author YU
 * @date 2022-05-04 17:42
 */
public class DecoratorApplication {

    public static void main(String[] args) {

        // 创建文件名需要生成的或是读取的
        FileDataSource fileDataSource = new FileDataSource("a.txt");
        String filePath = fileDataSource.writeData();
        String sourceData = fileDataSource.readData();
        // 在此基础上增加功能：加解密和压缩，装饰者模式，最常想到的是将加解密，压缩方式写到通用工具类内
        // 在FileDataSource的类内前后添加方法；存在问题：1. 不符合开闭原则，2. 当需求随意组合变动的时候，不方便扩展和灵活变动
        // 采用装饰者模式增强方法，则可以单独组合使用
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        EncryptionDecorator decorator = new EncryptionDecorator(fileDataSource);
        String filePath01 = decorator.writeData();
        String copyData = decorator.readData();
    }
}
