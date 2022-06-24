package com.yu.demolookup.lookup.annotest;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author YU
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeClazz {

    private int number;

    public PrototypeClazz() {
        this.number = new Random().nextInt(1000);
    }

    public void testGet() {
        System.out.println("get a fresh Object number: " + number);
    }

}
