package com.yu.state;

/**
 * 状态模式: 不同的状态对应不同的处理，类似于策略模式，但是状态与状态直接是关联的，
 * 在实现的时候，往往是可以直接改变的，但是策略模式则是策略是完全不相同的实现没有关系
 *
 * @author YU
 * @date 2022-05-08 9:21
 */
public class StateApplication {
    public static void main(String[] args) {
        // 初始化并执行该状态下的动作: oneState
        OneState initState = new OneState();
        Context context = new Context(initState);
        context.dothis();

        // 改变状态并做其它的步骤： 由 oneState -> twoState
        context.dothat();
        context.dothis();

        // 再次改变状态，并做其它的步骤 由 twoState -> oneState
        context.dothat();
        context.dothis();

    }
}
