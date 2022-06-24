package com.yu.state;

import com.sun.istack.internal.Nullable;

/**
 * 状态one: 初始状态
 *
 * @author YU
 * @date 2022-05-08 9:39
 */
public class OneState extends ConcreteStates{
    private Context context;

    @Override
    public void dothis() {
        System.out.println("print oneState doing this state");
    }

    @Override
    public void dothat() {
        TwoState twoState = new TwoState();
        context.changeState(twoState);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
