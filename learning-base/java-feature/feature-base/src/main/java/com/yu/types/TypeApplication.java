package com.yu.types;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 泛型
 *
 * @author YU
 * @date 2022-05-26 8:28
 */
public class TypeApplication {

    public static void main(String[] args) {
        ArrayList<Long> longArrayList = new ArrayList<>();
        TypeApplication typeApplication = new TypeApplication();
        Long max = typeApplication.max(longArrayList);
        System.out.println(max);
    }

    private <E extends Comparable<? super E>> E max(List<? extends E> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        Iterator<? extends E> iterator = list.iterator();
        E result = iterator.next();
        while (result != null && iterator.hasNext()) {
            E next = iterator.next();
            if (next.compareTo(result) > 0) {
                result = next;
            }
        }

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(TypeApplication.class);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        return result;
    }
}
