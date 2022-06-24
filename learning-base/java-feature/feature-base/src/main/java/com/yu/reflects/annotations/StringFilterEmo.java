package com.yu.reflects.annotations;

/**
 * 过滤字符中表情符的注解
 *
 * @author YU
 * @date 2022-05-26 14:17
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface StringFilterEmo {

    /**
     * 是否开启注解，默认为true;
     *
     * @author YU
     * @return   {@link boolean}
     */
    boolean isFilter() default true;
}
