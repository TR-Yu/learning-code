package com.yu.prototype;

import com.alibaba.fastjson2.JSON;
import com.yu.prototype.demo1.BaseShape;
import com.yu.prototype.demo1.Circle;
import com.yu.prototype.demo1.Rectangle;
import com.yu.prototype.demo2.CloneUtils;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 原型模式：
 * 需要有一堆的对象需要自己克隆自己；
 * 关联知识：java的object类并没有实现cloneAble接口，需要子类实现并调用clone() 为 native方法；
 * 项目中若是使用的话，可以将克隆过程用 三方的序列化的类 封装为工具类实现了，替代clone()内的实现
 * 适用场景是不是：需要克隆一个有状态的bean时使用，
 * 深克隆和浅克隆
 * 序列化知识
 *
 * @author YU
 * @date 2022-04-30 23:57
 */
public class PrototypeApplication extends Object{

    public static void main(String[] args) {
        Circle circle = new Circle(1.00, 2.00, "red", new BigDecimal("1.03"));
        Rectangle rectangle = new Rectangle(2.01, 3.12, "yellow", 45.87, 43.09);
        ArrayList<BaseShape> baseShapes = new ArrayList<>();
        baseShapes.add(circle);
        baseShapes.add(rectangle);
        // 使用com.lang 下的序列化工具实现，需要实现 序列化接口 serilaztion 接口
        ArrayList<BaseShape> newShapes = CloneUtils.getClone(baseShapes);
        if (newShapes != null) {
            for (BaseShape newShape : newShapes) {
                baseShapes.add(newShape.clone());
            }
        }

        // 实现clone接口和重写clone方法，调用 clone（）方法得到的
        Circle cloneInterface = circle.clone();
        // 使用 fastJson，Gson，jackSon 实现的不需要实现序列化接口的，对于项目中使用友好
        String jsonString = JSON.toJSONString(rectangle);
        Circle fastJsonClone = JSON.parseObject(jsonString, Circle.class);
        Rectangle clone = rectangle.clone();
        baseShapes.add(fastJsonClone);
        baseShapes.add(clone);
        System.out.println(baseShapes.size());
    }

}
