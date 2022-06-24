package com.yu;

import com.yu.beans.IsEntityTest;
import com.yu.beans.annotion.Super;
import com.yu.beans.Admin;
import com.yu.beans.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * spring内的依赖查找
 * 1. 非即时的和正常取有什么区别？应用场景是啥，ObjectFactory 是什么？
 * 2. ListableBeanFactory 有什么用处，为什么可以强制转化
 *
 * @author YU
 * @date 2022-06-01 16:43
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        lookupByNameWithIsEntityTest(beanFactory);
        lookupByName(beanFactory);
        lookupByLazy(beanFactory);
        lookupSingleByType(beanFactory);
        lookupByMultiByType(beanFactory);
    }

    private static void lookupByMultiByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Admin> beansMap = listableBeanFactory.getBeansOfType(Admin.class);
            System.out.printf("多个类 %s%n",beansMap);
            Map<String, Object> beansWithAnnotation = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.printf("抽取出带注解的类 %s%n",beansWithAnnotation);
        } else {
            Admin bean = beanFactory.getBean(Admin.class);
            System.out.printf("查询到类型为单个的情况 %s%n",bean);
        }
    }

    private static void lookupSingleByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.printf("查询到类型为单个的情况 %s%n",user);
    }

    private static void lookupByLazy(BeanFactory beanFactory) {
        @SuppressWarnings("unchecked")
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.printf("非即时加载 %s%n",user);
    }

    /**
     * 按名称查找 | 即时查找
     *
     * @param beanFactory {@link BeanFactory}
     * @author YU
     */
    private static void lookupByName(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.printf("按名称查找 %s%n",user);
    }

    private static void lookupByNameWithIsEntityTest(BeanFactory beanFactory) {
        IsEntityTest bean = beanFactory.getBean("isEntityTest", IsEntityTest.class);
        System.out.println("is 是否可以" + bean.toString() + " isValid" + bean.getIsValid() );
    }
}
