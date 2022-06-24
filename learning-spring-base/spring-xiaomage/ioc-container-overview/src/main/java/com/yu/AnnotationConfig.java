package com.yu;

import com.yu.beans.Admin;
import com.yu.beans.Peoples;
import com.yu.beans.SuperAdmin;
import org.springframework.context.annotation.Bean;

/**
 * @author YU
 * @date 2022-06-14 20:21
 */
public class AnnotationConfig {

    /**
     * set注入？
     *
     * @param admin
     * @return
     */
    @Bean("peoples")
    public Peoples setPeoples(Admin admin) {
        Peoples peoples = new Peoples();
        peoples.setSudoAdmin(admin);
        return peoples;
    }

    @Bean("superAdmins")
    public SuperAdmin setSuperAdmin() {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setId(100);
        superAdmin.setAddress("西湖区");
        superAdmin.setName("ok,aiyouwei");
        return superAdmin;
    }

    @Bean("admin")
    public Admin setAdmin() {
        Admin admin = new Admin();
        admin.setId(1);
        admin.setName("UserAdmin");
        return admin;
    }
}
