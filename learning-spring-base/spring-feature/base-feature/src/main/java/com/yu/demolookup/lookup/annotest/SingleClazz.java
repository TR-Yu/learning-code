package com.yu.demolookup.lookup.annotest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author YU
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SingleClazz {

    public void testPrototype() {
        getPrototypeClazz().testGet();
    }

    /**
     * 使用注解方式，在调用的单例类内使用@Lookup注解，具体的使用说明和不可用情况见
     * @see https://www.baeldung.com/spring-lookup
     */
    @Lookup
    protected PrototypeClazz getPrototypeClazz() {
        return null;
    }
}
