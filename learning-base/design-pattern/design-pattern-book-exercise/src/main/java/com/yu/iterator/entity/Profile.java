package com.yu.iterator.entity;

/**
 * 实体
 *
 * @author YU
 * @date 2022-05-06 17:02
 */
public class Profile {
    private Long id;
    private String email;

    public Profile(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Profile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

}
