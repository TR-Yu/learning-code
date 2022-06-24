package com.yu.factory;

import com.yu.beans.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author YU
 * @date 2022-06-03 1:09
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return UserFactoryBean.createUserByFactoryBean();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    public static User createUserByFactoryBean(){
        User user = new User();
        user.setId(200);
        user.setName("factoryBean create bean: description in User");
        return user;
    }
}
