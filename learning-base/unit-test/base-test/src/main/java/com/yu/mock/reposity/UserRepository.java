package com.yu.mock.reposity;

import com.yu.mock.entity.User;

/**
 * @author YU
 * @date 2022-06-18 9:55
 */
public class UserRepository {
    public void saveUser(User user){
        System.out.println("将用户信息插入到数据库中");
    }

}
