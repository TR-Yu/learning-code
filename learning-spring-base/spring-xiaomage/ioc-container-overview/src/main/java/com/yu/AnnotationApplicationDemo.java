package com.yu;

import com.yu.beans.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于注解的方式
 *
 * @author YU
 * @date 2022-06-02 1:03
 */

public class AnnotationApplicationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationApplicationDemo.class);
        applicationContext.refresh();
        lookupByName(applicationContext);
    }

    @Bean({"user","findUser","users"})
    public User findUser() {
        User user = new User();
        user.setId(1);
        user.setName("annotation's name");
        return user;
    }

    private static void lookupByName(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("findUser");
        Object annotationApplicationDemo = beanFactory.getBean("annotationApplicationDemo");
        System.out.printf("按名称查找 %s%n", user);
        System.out.println(annotationApplicationDemo);
    }
}
