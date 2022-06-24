package com.yu;

import com.yu.beans.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * 基于xml方式的Bean信息配置 注册基于spring的机制读取
 * 基于注解的Bean信息配置：
 * 1 @Bean注解， @Component 注解 。 @import 注解 注册还是基于spring的注册方式读取的
 * 基于api的BeanDefinition的方式定义好，然后采用对应的api进行的注册
 *
 * @author YU
 * @date 2022-06-02 15:57
 */

@Import({BeanRegisterDemo.AnnotationConfig.class, BeanRegisterDemo.BeanConfig.class})
public class BeanRegisterDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationStartByBean(applicationContext);

        AnnotationConfigApplicationContext applicationContextOne = new AnnotationConfigApplicationContext();
        applicationStartByComponent(applicationContextOne);

        /**
         * 不需要Annotation注解的解析，只是为了说明 BeanDefinitionRegistry 接口的作用 ，
         * 对应的实现是由 对应的 ApplicationContext 实现
         *
         */
        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();
        //指定了注册时 bean 名称
        registerUserBeanDefinition(genericApplicationContext,"lookingUser");
        //未指定注册时 bean 的名称
        registerUserBeanDefinition(genericApplicationContext,null);
       genericApplicationContext.refresh();
        Map<String, User> beans = genericApplicationContext.getBeansOfType(User.class);
        beans.forEach((k,v) -> System.out.printf("key is %s, value is %s %n", k, v.toString()));
    }

    private static void registerUserBeanDefinition(BeanDefinitionRegistry registry, @Nullable String beanName) {
        //beanDefinition 注入bean信息
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", "0001")
                .addPropertyValue("name", "hello register api");

        //有无beanName的注入
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    private static void applicationStartByComponent(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.register(BeanRegisterDemo.class);
        applicationContext.refresh();
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
        System.out.println("---------- line ------------");
        Map<String, AnnotationConfig> beansOfType = applicationContext.getBeansOfType(AnnotationConfig.class);
        Map<String, BeanConfig> beansOfType1 = applicationContext.getBeansOfType(BeanConfig.class);
        beansOfType.forEach((k, v) -> {
            System.out.printf("key is %s, value is %s %n", k, v.user().toString());
        });
        beansOfType1.forEach((k, v) -> System.out.printf("key is %s, value is %s %n", k, v.findUser().toString()));
        System.out.println("---------- line -----------");

    }

    /**
     * 基于 @Bean 的方式进行 bean 的信息配置和注册
     *
     * @param applicationContext {@link AnnotationConfigApplicationContext}
     * @author YU
     */
    private static void applicationStartByBean(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.register(BeanConfig.class);
        applicationContext.refresh();
        User findUser = applicationContext.getBean("findUser", User.class);
        System.out.println(findUser);
        System.out.println("---------- line ------------");
    }

    public static class BeanConfig {
        @Bean
        public User findUser() {
            User user = new User();
            user.setId(200);
            user.setName("hello annotation register");
            return user;
        }
    }

    @Configuration
    public static class AnnotationConfig {
        @Bean("user")
        public User user() {
            User user = new User();
            user.setId(100);
            user.setName("hello annotation register by component");
            return user;
        }
    }
}
