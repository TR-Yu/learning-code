package com.yu;

import com.yu.beans.Peoples;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

/**
 * 注入样例
 * spring 的 ioc容器的 元信息： 1. 内部注入bean的信息， 2. 影响到ioc容器行为本身的信息  3. 外部注入信息 @value ?
 *
 * @author YU
 * @date 2022-06-01 21:29
 */

public class DependencyInjectDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-inject-context.xml");
        ConfigurableListableBeanFactory beanFactory1 = beanFactory.getBeanFactory();
        // 业务层自定义注入的bean
        Peoples peoples = beanFactory.getBean("peoples", Peoples.class);
        BeanFactory injectBeanFactory = peoples.getBeanFactory();
        peoples.getAdmins().forEach(System.out::println);
        System.out.println(injectBeanFactory);
        System.out.println(beanFactory);

        // 内部Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);

        //内部依赖实体
        Assert.isTrue(injectBeanFactory == beanFactory1,"is not same");

    }
}
