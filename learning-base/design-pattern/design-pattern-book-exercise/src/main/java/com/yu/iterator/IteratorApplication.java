package com.yu.iterator;

import com.yu.iterator.entity.Profile;
import com.yu.iterator.interfaces.SocialNetwork;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.Validate;

/**
 * 迭代器模式
 *
 * @author YU
 * @date 2022-05-06 17:01
 */
public class IteratorApplication {


    public static void main(String[] args) {
        IteratorApplication application = new IteratorApplication();
        application.config("facebook");
        Profile profile = new Profile(10L, "");
        application.sendSpamToFriends(profile);
        application.sendSpamToCoworkers(profile);
    }

    private SocialSpammer spammer;
    private SocialNetwork network;

    public void sendSpamToFriends(Profile profile) {
        ProfileIterator iterator = this.network.createCoworkersIterator(profile.getId());
        this.spammer.send(iterator, "Very important message");
    }

    public  void sendSpamToCoworkers(Profile profile) {
        ProfileIterator iterator = this.network.createCoworkersIterator(profile.getId());
        this.spammer.send(iterator,"Very important message");
    }

    void config(String applicationName) {
        Validate.notNull(applicationName, "applicationName is not null");
        switch (applicationName) {
            case "facebook":
            case "link":
                this.network = new Facebook();
                break;
            default:
                throw new NotImplementedException("对应的应用未实现");
        }
        this.spammer = new SocialSpammer();
    }

}
