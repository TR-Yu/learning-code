package com.yu.facotrymethod.product;

public class WebButtion implements Button{
    @Override
    public void render() {
        System.out.println("paint the green black");
    }

    @Override
    public void onClick(Function function) {
        System.out.println("begin, web");
        function.getStringValue();
        System.out.println("end, web");
    }
}
