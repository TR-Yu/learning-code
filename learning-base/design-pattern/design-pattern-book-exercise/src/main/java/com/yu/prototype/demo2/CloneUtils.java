package com.yu.prototype.demo2;

import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用于克隆的工具类
 *
 * @author YU
 * @date 2022-05-01 0:33
 */
public class CloneUtils {

    public static <T> T getClone(T source) {
        if (Objects.isNull(source)) {
            throw new IllegalArgumentException("入参为空");
        }
        byte[] serialize = SerializationUtils.serialize((Serializable) source);
        Object deserialize = SerializationUtils.deserialize(serialize);
        Class<?> aClass = Objects.requireNonNull(deserialize).getClass();
        if (aClass.isAssignableFrom(source.getClass())) {
            return (T) deserialize;
        }
        return null;
    }
}
