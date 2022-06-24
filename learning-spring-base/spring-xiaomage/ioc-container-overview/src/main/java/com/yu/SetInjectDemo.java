package com.yu;

import com.yu.beans.Peoples;
import com.yu.beans.SuperAdmin;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * set方式注入，
 *   手动绑定：
 *   xml的<property>标签的注入设置属性值 ref； 需要有set方法在注入的bean内                           xmlInject();
 *   api注解的方式： @Bean  然后在方法体内主动调用set()方法；                                       annotationInject();
 *   java代码的方式 采用BeanDefinitionBuilder#addPropertyReference, 或是 addPropertyValue的方式  javaApiInject();
 *
 *   自动绑定：
 *    是指通过xml的autowire 属性设置 byType 或是 byName的方式 自动注入到对应的属性值内
 *
 * 构造方式注入：
 *  手动注入
 *    xml的<constructor-arg></constructor-arg> : 内部参数需要和被注入的bean的结构相同
 *    api注入的方式 @Bean  需要主动调用Bean内的构造方法赋值
 *    java代码方式 {@link BeanDefinitionBuilder#addConstructorArgReference(String)} 或是
 *    {@link BeanDefinitionBuilder#addConstructorArgValue(Object)}来进行注入
 *   自动注入：
 *     xml的 autowire 属性 设置 constructor
 *
 * @author YU
 * @date 2022-06-14 20:00
 */
public class SetInjectDemo {
    public static void main(String[] args) {
        xmlInject();
        annotationInject();
        javaApiInject();
    }

    private static void xmlInject() {
        DefaultListableBeanFactory listableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(listableBeanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-inject-context.xml");
        Peoples peoples = listableBeanFactory.getBean("peoples", Peoples.class);
        System.out.println(peoples);
    }

    private static void annotationInject() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationConfig.class);
        context.refresh();

        Map<String, Peoples> beansOfType = context.getBeansOfType(Peoples.class);
        beansOfType.forEach((k, v) -> {
            System.out.println("key : " + k + " value : " + v);
        });
        context.close();
    }

    private static void javaApiInject() {
        DefaultListableBeanFactory listableBeanFactory = new DefaultListableBeanFactory();

        AbstractBeanDefinition superAdminBeanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(SuperAdmin.class)
                .addPropertyValue("id", 1)
                .addPropertyValue("name", "hello world")
                .addPropertyValue("address", "余杭区万里路街道")
                .getBeanDefinition();

        AbstractBeanDefinition peopleBeanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(Peoples.class)
                .addPropertyReference("sudoAdmin", "superAdmin")
                .getBeanDefinition();

        listableBeanFactory.registerBeanDefinition("superAdmin", superAdminBeanDefinition);
        listableBeanFactory.registerBeanDefinition("people", peopleBeanDefinition);

        System.out.println(listableBeanFactory.getBean("people"));

    }
}
