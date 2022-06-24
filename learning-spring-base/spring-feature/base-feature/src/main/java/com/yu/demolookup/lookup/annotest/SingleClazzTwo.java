package com.yu.demolookup.lookup.annotest;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author YU
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SingleClazzTwo {

    /**
     * no params and return result of void
     *
     * @author YU
     */
    public void testPrototype() {
        getPrototypeClazz().testGet();
    }

    /**
     * one
     *
     * @return {@link PrototypeClazz}
     * @author YU
     */
    public PrototypeClazz getPrototypeClazz() {
        return SpringManager.getBean(PrototypeClazz.class);
    }


}
