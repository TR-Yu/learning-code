package com.yu.facotrymethod.creator;


import com.yu.facotrymethod.product.Button;
import com.yu.facotrymethod.product.Function;

/**
 * @author YU
 */
public abstract class Dialog {

    /**
     * 给抽象方式具体的实现由子类实现
     *
     * @author YU
     * @return   {@link Button}
     */
    public abstract Button createButton();

    /**
     * 用于正常流程的处理，使用抽象类可以将创建对象的部分和具体的类别分离开来
     * 在一个更好级别的层次上使用不需要先实现具体的实体
     *
     * */
    public void renderDialog() {
        createButton().render();
        Function function = new Function();
        createButton().onClick(function);
    }



}
