package com.yu.javabean;

import java.util.StringJoiner;

/**
 * @author YU
 * @date 2022-06-12 13:24
 */
public class UserEntity {
    private boolean validated;
    private int id;

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserEntity.class.getSimpleName() + "[", "]")
                .add("validated=" + validated)
                .add("id=" + id)
                .toString();
    }
}
