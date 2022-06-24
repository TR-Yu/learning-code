package com.yu.reflects.handler;

import com.yu.reflects.annotations.StringFilterEmo;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 表情包过滤处理器: 必须是原型模式
 *
 * @author YU
 * @date 2022-05-26 14:23
 */
public class FilterAnnotationHandler<T> {

    private Class aClass;
    private T filterSource;
    private final String EMO_STRING = "[(0-9)]";
    private final String EMPTY_STRING = "";

    /**
     * 对外过滤表情符方法
     *
     * @param source {@link T}
     * @return {@link T}
     * @author YU
     */
    public T filterEmo (T source) {
        initHandler(source);
        Set<String> fieldSet = getFilterFields();
        if (fieldSet.isEmpty()) {
            return this.filterSource;
        }

        fieldSet.forEach(el -> {
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(el, this.aClass);
                Method readMethod = propertyDescriptor.getReadMethod();
                Method writeMethod = propertyDescriptor.getWriteMethod();
                String str = (String) readMethod.invoke(this.filterSource);
                writeMethod.invoke(this.filterSource, this.filterEmoString(str));
            } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
       return this.filterSource;
    }

    private void initHandler(T source) {
        if (source == null) {
            return;
        }
        this.aClass = source.getClass();
        this.filterSource = source;
    }

    /**
     * 通过反射获取到放入的对象中需要修改的属性值集合
     *
     * @return {@link Set<String>}
     * @author YU
     */
    private Set<String> getFilterFields() {
        Field[] declaredFields = this.aClass.getDeclaredFields();
        Set<String> filedStr = Arrays.stream(declaredFields)
                                     .filter(el -> el.isAnnotationPresent(StringFilterEmo.class))
                                     .filter(el -> el.getAnnotation(StringFilterEmo.class).isFilter())
                                     .map(Field::getName)
                                     .collect(Collectors.toSet());
        return filedStr;
    }

    /**
     * 过滤字符串中的特殊表情符方法
     *
     * @param filterStr {@link String}
     * @return {@link String}
     * @author YU
     */
    private String filterEmoString(String filterStr) {
        return filterStr.replaceAll(EMO_STRING, EMPTY_STRING);
    }
}
