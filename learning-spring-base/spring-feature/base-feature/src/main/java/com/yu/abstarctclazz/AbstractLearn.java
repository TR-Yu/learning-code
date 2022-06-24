package com.yu.abstarctclazz;

import org.springframework.stereotype.Component;

/**
 * 抽象类
 *
 * @author YU
 * @date 2022-05-09 17:59
 */
@Component("abstractLearn")
public abstract class AbstractLearn {

    public abstract void print();

    public void plant(){
        System.out.println("abstract plant method");
    }
}
