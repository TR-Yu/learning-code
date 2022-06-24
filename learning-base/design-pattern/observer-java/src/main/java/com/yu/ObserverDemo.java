package com.yu;

import com.yu.observable.ObserverEnum;

import java.util.Observable;
import java.util.Observer;

/**
 * java 的简单的观察者模式的实现 基于 jdk的标准类库 {@link Observable} 和 {@link Observer}
 * Java结构示意图: {link https://excalidraw.com/#json=6_KQXP-k29kTf722mYhfa,gJl-wz2fU2g4sOrIBSyZxw}
 * @author YU
 * @date 2022-06-24 9:01
 */
public class ObserverDemo {
    public static void main(String[] args) {

        ObserverManager observerManager = new ObserverManager(ObserverEnum.FIREWORK.getGroup());

        observerManager.doSomethingAndNotifyObservers("work start", () -> {
            System.out.println("事件源产生一些动作，然后通过被观察者发布：开始工作");
        });

        observerManager.doSomethingAndNotifyObservers("print start", () -> {
            System.out.println("事件源产生一些动作，然后通过被观察者发布：开始打印");
        });

        observerManager.doSomethingAndNotifyObservers(null, () -> {
            System.out.println("事件源产生一些动作，然后通过被观察者发布：开始");
        });

    }
}
