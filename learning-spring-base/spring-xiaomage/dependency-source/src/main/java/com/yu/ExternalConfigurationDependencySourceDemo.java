package com.yu;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;

import java.util.Map;

/**
 * @author YU
 * @value 注入的外部依赖来源
 * @date 2022-06-23 13:47
 */
@PropertySource(value = "META-INF/default.properties",encoding = "UTF-8")
@Configuration
public class ExternalConfigurationDependencySourceDemo {

    @Value("${usr.name:Yu}")
    private String userName;

    @Value("${user.id:1}")
    private Long id;

    @Value("${user.resource:classpath://default.properties}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.registerBean(ExternalConfigurationDependencySourceDemo.class);

        applicationContext.refresh();
        ExternalConfigurationDependencySourceDemo dependencySourceDemo =
                applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);
        System.out.println(dependencySourceDemo.id);
        System.out.println(dependencySourceDemo.userName);
        System.out.println(dependencySourceDemo.resource);
        applicationContext.close();
    }
}
