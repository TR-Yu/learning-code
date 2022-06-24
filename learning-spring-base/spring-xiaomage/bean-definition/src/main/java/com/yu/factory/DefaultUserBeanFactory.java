package com.yu.factory;

import com.yu.beans.User;

/**
 * @author YU
 * @date 2022-06-03 1:27
 */
public class DefaultUserBeanFactory implements CreateFactoryInterface {

    @Override
    public User findUser() {
        User user = new User();
        user.setId(2000);
        user.setName("serviceLoader");
        return user;
    }
}
