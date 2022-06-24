package com.yu.beans;

import java.util.StringJoiner;

/**
 * 用于测试是否is会导致问题
 *
 * @author YU
 * @date 2022-06-12 12:57
 */
public class IsEntityTest {
    private boolean isValid ;

    private boolean empty;

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean valid) {
        isValid = valid;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", IsEntityTest.class.getSimpleName() + "[", "]")
                .add("isValid=" + isValid)
                .toString();
    }
}
