package com.yu.factory;

import com.yu.beans.User;

/**
 * 创建bean工厂方法抽象接口
 *
 * @author YU
 * @date 2022-06-03 0:48
 */
public interface CreateFactoryInterface {
    /**
     * 查询到user对象的方法
     *
     * @return {link User}
     */
    default User findUser(){
        User user = new User();
        user.setId(1000);
        user.setName("factory interface");
        return user;
    }

    /**
     * id,获取到对应的User对象
     *
     * @author YU
     * @param id  {@link Integer}
     * @return   {@link User}
     */
    default User findUserById(Integer id){
        return new User();
    }
}
