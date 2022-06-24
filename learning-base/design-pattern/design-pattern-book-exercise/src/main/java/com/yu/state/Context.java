package com.yu.state;

/**
 * 是state的上下文用于state的切换
 *
 * @author YU
 * @date 2022-05-08 9:25
 */
public class Context {
    private State state;

    public Context(State initialState) {
        this.state = initialState;
        this.state.setContext(this);
    }

    public void changeState(State currentState){
        this.state = currentState;
        this.state.setContext(this);
    }

    public void dothis(){
        this.state.dothis();
    }

    public void dothat(){
        this.state.dothat();
    }

}
