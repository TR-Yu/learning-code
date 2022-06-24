package com.yu.abstarctclazz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试目的：spring对于抽象类是否可以进行 spring bean 的实例化;
 * 测试结果：抽象类和接口，注解均不可以实例化
 *
 * @author YU
 * @date 2022-05-09 17:57
 */
@SpringBootApplication
public class AbstractClazzApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AbstractClazzApplication.class);
        Leaning abstractLearn = run.getBean("leaning", Leaning.class);
        abstractLearn.plant();
        abstractLearn.print();
    }
}
