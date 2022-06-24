package com.yu.demolookup.lookup.annotest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author YU
 */
@Component
public class SpringManager implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContextAware) throws BeansException {
        applicationContext = applicationContextAware;
    }

    /**
     * 缺少参数校验，还有捕获异常
     */
    public static <T> T getBean(Class<T> clazz) {
        Assert.notNull(clazz, "not null");
        return applicationContext.getBean(clazz);
    }
}
