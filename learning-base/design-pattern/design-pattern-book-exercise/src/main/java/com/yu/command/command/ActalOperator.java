package com.yu.command.command;

/**
 * 实际执行者
 *
 * @author YU
 * @date 2022-05-05 11:09
 */
public class ActalOperator {

    public void print(String num) {
        switch (num) {
            case "one" :
                System.out.println(num + "is one"); break;
            case "two" :
                System.out.println(num + "is two"); break;
            case "three" :
                System.out.println(num + "is three"); break;
            default:
        }
    }
}
