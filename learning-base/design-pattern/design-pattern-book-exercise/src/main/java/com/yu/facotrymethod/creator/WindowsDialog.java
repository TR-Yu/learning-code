package com.yu.facotrymethod.creator;

import com.yu.facotrymethod.product.Button;
import com.yu.facotrymethod.product.WindowButtion;

/**
 * @author YU
 */
public class WindowsDialog extends Dialog {

    @Override
    public Button createButton() {
        return new WindowButtion();
    }

}
