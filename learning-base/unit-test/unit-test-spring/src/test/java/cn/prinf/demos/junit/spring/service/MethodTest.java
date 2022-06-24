package cn.prinf.demos.junit.spring.service;

import org.junit.Test;

import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * @author YU
 * @date 2022-06-20 9:25
 */
public class MethodTest {
    private volatile double x = Math.PI;

    @Test
    public void measureShared() {
        DoubleAccumulator accumulator = new DoubleAccumulator(Double::sum,x);
        accumulator.accumulate(1.00D);
        System.out.println(x);
    }
}
