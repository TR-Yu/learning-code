package com.yu.facotrymethod.creator;

import com.yu.facotrymethod.product.Button;
import com.yu.facotrymethod.product.IosButton;

/**
 * @author YU
 */
public class IosDialog extends Dialog {

    @Override
    public Button createButton() {
        return new IosButton();
    }
}
