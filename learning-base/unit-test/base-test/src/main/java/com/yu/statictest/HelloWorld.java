package com.yu.statictest;

import com.yu.statictest.DivideByZeroException;

/**
 * @author YU
 * @date 2022-06-17 14:52
 */
public class HelloWorld {
    public static String printStr(){
        String printStr = "Hello Unit-Test";
        return printStr;
    }

    public static float calculate(float totalPrice, float counts) {
        return totalPrice / counts;
    }

    public static int divide(int dividend, int divisor) {
        if (divisor == 0){
            throw new DivideByZeroException("divisor must not zero");
        }
        int result = 0;
        float remainder = dividend;
        while (remainder >= divisor) {
            result ++;
            remainder -= divisor;
        }
        return result;
    }
}
