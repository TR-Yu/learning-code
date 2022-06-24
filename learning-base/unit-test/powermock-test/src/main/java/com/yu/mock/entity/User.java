package com.yu.mock.entity;

import java.time.Instant;

/**
 * @author YU
 * @date 2022-06-18 9:56
 */
public class User {
    private String email;
    private String password;
    private String name;
    private Instant createAt;

    public User() {
    }

    public User(String email, String password, String name, Instant createAt) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.createAt = createAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }
}
