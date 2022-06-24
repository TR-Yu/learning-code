package com.yu.iterator.interfaces;

import com.yu.iterator.ProfileIterator;

/**
 * 社交网络接口
 *
 * @author YU
 * @date 2022-05-06 18:43
 */
public interface SocialNetwork {

    /**
     * 获取朋友关系网
     *
     * @return {@link ProfileIterator}
     * @param profiledId {@link Long}
     * @author YU
     */
    ProfileIterator createFriendIterator(Long profiledId);

    /**
     * 用于获取同事迭代器
     *
     * @author YU
     * @param profiledId {@link Long}
     * @return   {@link ProfileIterator}
     */
    ProfileIterator createCoworkersIterator(Long profiledId);
}
