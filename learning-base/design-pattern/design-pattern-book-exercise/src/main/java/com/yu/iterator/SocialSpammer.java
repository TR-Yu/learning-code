package com.yu.iterator;

import com.yu.iterator.entity.Profile;
import com.yu.iterator.interfaces.Iterator;

/**
 * 社交查询
 *
 * @author YU
 * @date 2022-05-06 19:01
 */
public class SocialSpammer {

    public void send(Iterator iterator, String message) {
        while (iterator.hasMore()){
            Profile profile = iterator.getNext();
            System.out.println(profile.getEmail() + message);
        }
    }
}
