package com.yu.facotrymethod.product;

public class WindowButtion implements Button{
    @Override
    public void render() {
        System.out.println("painting a gery black and a transparent font");
    }

    @Override
    public void onClick(Function function) {
        System.out.println("jump to the link in hidden");
        function.getStringValue();
        System.out.println("show the website in Windows");
    }
}
