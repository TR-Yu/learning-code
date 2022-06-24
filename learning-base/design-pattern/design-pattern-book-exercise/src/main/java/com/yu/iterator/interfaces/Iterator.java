package com.yu.iterator.interfaces;

import com.yu.iterator.entity.Profile;

/**
 * 迭代器
 *
 * @author YU
 * @date 2022-05-06 17:14
 */
public interface Iterator {

    /**
     * 获取元素
     *
     * @return {@link Profile}
     * @author YU
     */
    Profile getNext();

    /**
     * 是否有下一个元素
     *
     * @return {@link boolean}
     * @author YU
     */
    boolean hasMore();
}
