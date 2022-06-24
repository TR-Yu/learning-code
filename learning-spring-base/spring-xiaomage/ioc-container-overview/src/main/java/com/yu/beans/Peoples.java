package com.yu.beans;

import org.springframework.beans.factory.BeanFactory;

import java.util.Collection;
import java.util.StringJoiner;

/**
 * @author YU
 * @date 2022-06-01 21:49
 */
public class Peoples {
    private Collection<Admin> admins;
    private BeanFactory beanFactory;
    private Admin sudoAdmin;

    public Collection<Admin> getAdmins() {
        return admins;
    }


    public void setAdmins(Collection<Admin> admins) {
        this.admins = admins;
    }

    public Admin getSudoAdmin() {
        return sudoAdmin;
    }

    public void setSudoAdmin(Admin sudoAdmin) {
        this.sudoAdmin = sudoAdmin;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Peoples.class.getSimpleName() + "[", "]")
                .add("admins=" + admins)
                .add("beanFactory=" + beanFactory)
                .add("sudoAdmin=" + sudoAdmin)
                .toString();
    }
}
