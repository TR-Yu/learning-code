package com.yu.facotrymethod.product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 执行的方法类
 *
 * @author YU
 * */
public class Function {

    private static String methodName;

    public synchronized void  getStringValue(){
        if (methodName == null) {
            methodName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd+HH:ss"));
        }

        System.out.println(methodName);
    }
}
