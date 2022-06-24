package com.yu.demolookup;

import com.yu.demolookup.lookup.annotest.LookUpTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 测试 @LookUp 使用
 * 1. 使用ware回调方法，将新生成的bean注入
 * 2. 使用 @LookUp bean注入
 *
 * 并实现了，两种方式的
 *
 * @author YU
 */
@AutoConfigureAfter
@SpringBootApplication
public class DemolookupApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemolookupApplication.class, args);
        LookUpTest lookUpTest = run.getBean("lookUpTest", LookUpTest.class);
        lookUpTest.getAnnotationView();
        lookUpTest.getImplementationView();
    }
}