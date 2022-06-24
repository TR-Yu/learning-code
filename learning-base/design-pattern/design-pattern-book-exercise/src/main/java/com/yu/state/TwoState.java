package com.yu.state;

/**
 * 状态two: 第二种状态
 *
 * @author YU
 * @date 2022-05-08 9:41
 */
public class TwoState extends ConcreteStates{

    private Context context;

    @Override
    public void dothis() {
        System.out.println("print twoState doing this state");
    }

    @Override
    public void dothat() {
        OneState oneState = new OneState();
        context.changeState(oneState);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
