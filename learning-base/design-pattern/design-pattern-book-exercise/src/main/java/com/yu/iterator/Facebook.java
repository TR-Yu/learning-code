package com.yu.iterator;

import com.sun.istack.internal.NotNull;
import com.yu.iterator.entity.Profile;
import com.yu.iterator.interfaces.SocialNetwork;
import org.apache.commons.lang.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * facebook社交网
 *
 * @author YU
 * @date 2022-05-06 18:53
 */
public class Facebook implements SocialNetwork {

    @Override
    public ProfileIterator createFriendIterator(@NotNull Long profiledId) {
        return new FacebookIterator(this, profiledId, "friends");
    }

    @Override
    public ProfileIterator createCoworkersIterator(@NotNull Long profiledId) {
        return new FacebookIterator(this, profiledId, "coworkers");
    }

    public Profile[] socialGraphRequest(@NotNull Long profileId, @NotNull String type) {

        Validate.notNull(profileId, "profileId must not null");
        Validate.notNull(type, "type is not null");

        // 根据type获取，profileId 获取到对应的联系方式
        Profile profile01 = new Profile(10L, "12345@gmail.com");
        Profile profile02 = new Profile(1L, "234567@163.com");
        Profile profile03 = new Profile(10L, "23456@163.com");

        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile01);
        profiles.add(profile02);
        profiles.add(profile03);

        if (profiles.isEmpty()) {
            return new Profile[0];
        }

        List<Profile> listResult = profiles.stream().filter(el -> profileId.equals(el.getId())).collect(Collectors.toList());
        Profile[] a = new Profile[listResult.size()];
        return listResult.toArray(a);

    }
}
