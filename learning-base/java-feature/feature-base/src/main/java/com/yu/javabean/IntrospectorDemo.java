package com.yu.javabean;

import java.beans.*;
import java.lang.reflect.Method;

/**
 * 测试javabean规范的使用的是 java 标准库的内省机制
 *
 * @author YU
 * @date 2022-06-12 13:23
 */
public class IntrospectorDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(UserEntity.class,Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

    }
}
