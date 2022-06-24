package com.yu;

import com.yu.beans.Admin;
import com.yu.beans.SuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author YU
 * @date 2022-06-15 1:59
 */

public class AnnotationFiledDemo {

    @Resource
    private Admin admin1;

    private Admin admin2;

    private Admin admin3;

    private SuperAdmin superAdmin;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationFiledDemo.class);
        applicationContext.refresh();

        Map<String, AnnotationFiledDemo> beansOfType = applicationContext.getBeansOfType(AnnotationFiledDemo.class);

        beansOfType.forEach((k, v) -> {
            StringBuilder append = new StringBuilder("key: " + k)
                    .append(" value: admin1:")
                    .append(v.admin1)
                    .append(" admin2: ")
                    .append(v.admin2)
                    .append(" admin3: ")
                    .append(v.admin3)
                    .append("superAdmin: ")
                    .append(v.superAdmin);

            System.out.println(append);
            System.out.println((v.admin1 == v.admin2) && (v.admin2 == v.admin3));
        });
    }

    @Bean
    @Primary
    public Admin beanInfo() {
        Admin admin = new Admin();
        admin.setId(100);
        admin.setName("卧槽，讨厌死了");
        return admin;
    }

    @Bean
    public SuperAdmin adminBean() {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setId(1000);
        superAdmin.setName("OK，good compare");
        superAdmin.setAddress("杭州余杭区");
        return superAdmin;
    }

    @Autowired
    public void initialize1(Admin admin, SuperAdmin superAdmin) {
        admin.setName("had modified");
        this.admin2 = admin;
        this.superAdmin = superAdmin;
    }

    @Resource
    public void initialize2(Admin admin) {
        this.admin3 = admin;
    }

}
