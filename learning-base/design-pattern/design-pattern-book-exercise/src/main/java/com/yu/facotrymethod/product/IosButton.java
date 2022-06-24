package com.yu.facotrymethod.product;

public class IosButton implements Button{

    @Override
    public void render() {
        System.out.println("painting a blue black ");
    }

    @Override
    public void onClick(Function function) {
        System.out.println("begin,ios");
        function.getStringValue();
        System.out.println("end,ios");
    }
}
