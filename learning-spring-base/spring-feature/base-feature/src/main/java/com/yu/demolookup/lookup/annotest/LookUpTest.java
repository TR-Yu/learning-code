package com.yu.demolookup.lookup.annotest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取一个对象 @{@link SingleClazz}
 *
 * @author tryu
 */
@Component
public class LookUpTest {

    @Autowired
    SingleClazz singleClazz;

    @Autowired
    SingleClazzTwo singleClazzTwo;

    /**
     *
     * @author tryu
     */
    public void getAnnotationView() {
        for (int i = 0; i < 4; i++) {
            singleClazz.testPrototype();
        }
    }

    /**
     * 很多
     * @author tryu
     */
    public void getImplementationView() {
        for (int i = 0; i < 4; i++) {
            singleClazzTwo.testPrototype();
        }
    }

}
