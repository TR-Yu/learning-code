package com.yu.state;

import com.sun.istack.internal.Nullable;

/**
 * 状态接口，定义了不同状态下的通用操作
 *
 * @author YU
 * @date 2022-05-08 9:21
 */
public interface State {

    void dothis();

    void dothat();

    void setContext (@Nullable Context context);
}
