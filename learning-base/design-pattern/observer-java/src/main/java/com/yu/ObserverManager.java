package com.yu;

import com.yu.observable.ObserverEnum;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 注册中心：用于观察者和被观察者的关系注册中心
 * 一个被观察者，多个观察者的模型; 用于管理
 *
 * @author YU
 * @date 2022-06-24 9:18
 */
public class ObserverManager {

    private Observable observable;
    private String group;

    public ObserverManager(String group) {
        this.group = group;
        ObserverEnum[] enums = ObserverEnum.values();
        for (ObserverEnum observerEnum : enums) {
            if (Objects.equals(observerEnum.getGroup(),group)){
                try {
                    this.observable = (Observable) observerEnum.getObservable().newInstance();
                    List<Class> observerClazz = observerEnum.getObservers();
                    for (Class el : observerClazz) {
                        Observer o = null;
                        o = (Observer) el.newInstance();
                        observable.addObserver(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * 删除指定的或是一组的注册对象
     * @param observers
     */
    public void unregisterObservers(Observer... observers) {
        if (observers != null){
            Arrays.stream(observers).forEach(observer -> observable.deleteObserver(observer));
        }
    }

    public Observable getObservable() {
        return this.observable;
    }

    public void doSomethingAndNotifyObservers(String args, Runnable action){
        setChangedState(true);
        action.run();
        observable.notifyObservers(args);
    }

    public void setChangedState(boolean switchFlag) {
        try{
            Class<? extends Observable> aClass = observable.getClass();
            Field changed = aClass.getSuperclass().getDeclaredField("changed");
            changed.setAccessible(true);
            changed.set(observable,switchFlag);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
