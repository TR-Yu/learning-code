package com.yu;

import com.yu.beans.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * spring-Bean的创建和定义
 * 熟悉一些常见的api
 * BeanNameGenerator interface
 * DefaultBeanNameGenerator 默认实现的，xml配置的时候bean，使用的就是
 * AnnotationBeanDefinition 基于注解的，注意这个是类注解上才会使用，采用@Bean 方式注入的则不会使用
 *
 * @author YU
 * @date 2022-06-02 10:44
 */

public class BeanDefinitionDemo {

    /**
     * 创建BeanDefinition的方式：
     * 1. 通过BeanDefinitionBuilder；这是一个对所有的BeanDefinition实现类的对外创建的总体包装
     * 2. 创建一个GenericBeanDefinition;
     * @param args
     */
    public static void main(String[] args) {
        // 定义beanDefinition 通过 builder 的方式
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", "0001")
                .addPropertyValue("name", "hello spring");
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // 定义beanDefinition 通过
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues
                .add("id","0002")
                .add("name", "shift diff");
        genericBeanDefinition.setPropertyValues(propertyValues);

    }
}
