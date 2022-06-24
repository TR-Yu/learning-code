package com.yu.statictest;

/**
 * @author YU
 * @date 2022-06-17 15:30
 */
public class DivideByZeroException extends RuntimeException{
    public DivideByZeroException(String message) {
        super(message);
    }
}
