package com.yu.reflects;

import com.yu.reflects.entity.Entity;
import com.yu.reflects.handler.FilterAnnotationHandler;

/**
 * 表情符过滤需求，注解+反射机制实现
 *
 * @author YU
 * @date 2022-05-26 14:13
 */
public class ReflectApplication {
    public static void main(String[] args) {
        Entity entity = new Entity();
        entity.setAddress("one hard 12345 ");
        entity.setGoodsInfoName("          ");
        entity.setNickname("land's 12345 space45678 ");

        FilterAnnotationHandler<Entity> entityFilterAnnotationHandler = new FilterAnnotationHandler<>();
        Entity filterEmoEntity = entityFilterAnnotationHandler.filterEmo(entity);
        System.out.println(filterEmoEntity.toString());

    }
}
