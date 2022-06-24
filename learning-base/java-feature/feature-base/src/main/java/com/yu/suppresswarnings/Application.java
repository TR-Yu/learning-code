package com.yu.suppresswarnings;

import com.yu.suppresswarnings.Machine;

import java.util.ArrayList;

/**
 * java基础测试类
 *
 * @author YU
 * @date 2022-05-05 21:25
 */
public class Application {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("one");
        strings.add("two");
        strings.forEach((k) -> System.out.println("print one in " + k));
        Machine machine = new Machine(strings);
        machine.addVersion("three");

    }

}
