package com.yu.observer;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * @author YU
 * @date 2022-06-24 9:03
 */
public class PrintObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        String singleMessage = "print start";
        if (Objects.equals(singleMessage,arg)) {
            System.out.println("if the print is start, worker is printing now ");
        }
        System.out.println("Print do any thing");
    }
}
