package com.yu.observer;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * @author YU
 * @date 2022-06-24 9:05
 */
public class FinshObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        String singleMessage = "work start";
        if (Objects.equals(singleMessage,arg)) {
            System.out.println("if the work is start, worker is doing something util the work is finish");
        }

        System.out.println("finsh do anything");
    }

}
