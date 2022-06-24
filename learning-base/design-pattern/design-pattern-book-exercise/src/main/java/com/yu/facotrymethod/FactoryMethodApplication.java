package com.yu.facotrymethod;

import com.yu.facotrymethod.creator.Dialog;

import java.util.Random;

/**
 * @author YU
 */
public class FactoryMethodApplication {

    public static void main(String[] args) {
        final String[] deviceFlag = new String[] {"windows","ios","web"};
        int i = new Random().nextInt(3);
        Dialog dialog = Factory.createDialog(deviceFlag[i]);
        dialog.createButton();
        dialog.renderDialog();
    }
}
