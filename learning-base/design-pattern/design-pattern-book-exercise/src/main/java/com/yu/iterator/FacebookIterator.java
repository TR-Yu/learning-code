package com.yu.iterator;

import com.yu.iterator.entity.Profile;
import com.yu.iterator.interfaces.Iterator;

/**
 * facebook的实体迭代器
 *
 * @author YU
 * @date 2022-05-06 18:51
 */
public class FacebookIterator implements ProfileIterator {

    private final Facebook facebook;
    private final Long profileId;
    private final String type;
    private int currentPosition;
    private Profile[] caches;

    private void lazyInit() {
        if (caches == null) {
            this.caches = facebook.socialGraphRequest(profileId,type);
        }
    }


    public FacebookIterator(Facebook facebook, Long profileId, String type) {
        this.facebook = facebook;
        this.profileId = profileId;
        this.type = type;
    }

    @Override
    public Profile getNext() {
        if (hasMore()) {
            currentPosition ++;
        }
        return caches[currentPosition];
    }

    @Override
    public boolean hasMore() {
        lazyInit();
        return (caches.length -1) > currentPosition;
    }

    public Facebook getFacebook() {
        return facebook;
    }

}
