package com.yu.observable;

import com.yu.observer.FinshObserver;
import com.yu.observer.PrintObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于配置一个背观察者和多个观察者的依赖信息，用于抽象分组
 *
 * @author YU
 * @date 2022-06-24 9:37
 */
public enum ObserverEnum {
    /**
     *
     */
    FIREWORK("工作任务", OfficeWorkObservable.class,getOfficeWorkObservers());

    /**
     * 分组
     */
    private String group;

    /**
     * 观察者
     */
    private List<Class> observers;
    /**
     * 被观察者
     */
     private Class observable;

    ObserverEnum(String group, Class observable, List<Class> observers) {
        this.group = group;
        this.observers = observers;
        this.observable = observable;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<Class> getObservers() {
        return observers;
    }

    public void setObservers(List<Class> observers) {
        this.observers = observers;
    }

    public Class getObservable() {
        return observable;
    }

    public void setObservable(Class observable) {
        this.observable = observable;
    }

    // region 配置关系

    private static List<Class> getOfficeWorkObservers(){
        ArrayList<Class> observerClazz = new ArrayList<>();
        observerClazz.add(FinshObserver.class);
        observerClazz.add(PrintObserver.class);
        return observerClazz;
    }

    // endregion

}
