package com.yu.facotrymethod.creator;

import com.yu.facotrymethod.product.Button;
import com.yu.facotrymethod.product.WebButtion;

/**
 * @author YU
 */
public class WebDialog extends Dialog {

    @Override
    public Button createButton() {
        return new WebButtion();
    }
}
