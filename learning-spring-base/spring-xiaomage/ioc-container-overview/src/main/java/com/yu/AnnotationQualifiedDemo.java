package com.yu;

import com.yu.annotation.UserGroup;
import com.yu.beans.Admin;
import com.yu.beans.SuperAdmin;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author YU
 * @date 2022-06-15 13:16
 */
public class AnnotationQualifiedDemo {

    @Resource
    @Qualifier("superAdmin2")
    private Optional<SuperAdmin> superAdminOptional;

    /**
     * 测试多个Admin实例存在，使用 @Primary 以后，默认使用的实例
     */
    @Autowired
    private Admin admin01;

    /**
     * 测试多个Admin实例存在，使用 @Qualifier 限定了注入的bean的名称以后使用的实例
     *
     */
    @Autowired
    @Qualifier("user2")
    private Admin admin02;

    /**
     * 测试 @Resource内的name 是否可以和 @Qualifier + @Autowired 连用是否一致
     */
    @Resource(name = "user3")
    @Lazy
    private Admin admin03;

/*    *//**
     * 所有的ByType获取到的Admin的注入
     *//*
    @Autowired
    private List<Admin> allAdminList;

    *//**
     * 所有的使用@qualifier修饰的Admin实例 --- 分组功能
     *//*
    @Autowired
    @Qualifier
    private List<Admin> qualifiedAdminList;

    *//**
     * 所有的使用@UserGroup修饰的Admin实例 --- 分组功能
     *//*
    @Autowired
    @UserGroup
    private List<Admin> userGroupsList;*/

    /**
     * 使用ObjectProvider做为包装延迟对内部的延迟加载
     */
    @Autowired
    @Qualifier("superAdmin2")
    private ObjectProvider<SuperAdmin> objectProvider;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationQualifiedDemo.class);
        applicationContext.refresh();

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        AnnotationQualifiedDemo bean = applicationContext.getBean("annotationQualifiedDemo", AnnotationQualifiedDemo.class);
        System.out.println("@Primary admin01 " + bean.admin01);
        System.out.println("@Qualifier admin02 " + bean.admin02);
        System.out.println("@Resource name  admin03 " + bean.admin03);
      /*  System.out.println("all Admin " + bean.allAdminList.toString());
        System.out.println("@Qualifier group " + Arrays.toString(bean.qualifiedAdminList.toArray()));
        System.out.println("@UserGroup group " + Arrays.toString(bean.userGroupsList.toArray()));*/
        System.out.println("ObjectProvider#superAdmin " + bean.objectProvider.getIfAvailable());
        System.out.println("Optional#superAdmin" + bean.superAdminOptional.toString());
    }

    @Bean
    @Primary
    @Lazy
    public Admin user1() {
        Admin admin = new Admin();
        admin.setId(1);
        admin.setName("admin001");
        return admin;
    }

    @Bean
    @Lazy
    public Admin user2() {
        Admin admin = new Admin();
        admin.setId(2);
        admin.setName("admin002");
        return admin;
    }

    @Bean
    @Qualifier
    @Lazy
    public Admin user3() {
        Admin admin = new Admin();
        admin.setId(3);
        admin.setName("admin003");
        return admin;
    }

    @Bean
    @UserGroup
    @Lazy
    public Admin user4() {
        Admin admin = new Admin();
        admin.setId(4);
        admin.setName("admin004");
        return admin;
    }

    @Bean
    @Lazy
    public Admin user5() {
        Admin admin = new Admin();
        admin.setId(5);
        admin.setName("admin005");
        return admin;
    }

    @Bean
    @Lazy
    public SuperAdmin superAdmin1() {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setId(101);
        superAdmin.setName("superAdmin101");
        superAdmin.setAddress("杭州");
        return superAdmin;
    }

    @Bean
    @Lazy
    public SuperAdmin superAdmin2() {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setId(102);
        superAdmin.setName("superAdmin102");
        superAdmin.setAddress("杭州");
        return superAdmin;
    }

    @Bean
    @Qualifier
    @Lazy
    public SuperAdmin superAdmin3() {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setId(103);
        superAdmin.setName("superAdmin103");
        superAdmin.setAddress("杭州");
        return superAdmin;
    }

    @Bean
    @UserGroup
    @Lazy
    public SuperAdmin superAdmin4() {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setId(104);
        superAdmin.setName("superAdmin104");
        superAdmin.setAddress("上海");
        return superAdmin;
    }

    @Bean
    @Lazy
    public SuperAdmin superAdmin5() {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setId(105);
        superAdmin.setName("superAdmin105");
        superAdmin.setAddress("广州");
        return superAdmin;
    }

}
