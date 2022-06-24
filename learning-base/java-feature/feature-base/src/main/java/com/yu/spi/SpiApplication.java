package com.yu.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * spi测试:
 *
 *
 * @author YU
 * @date 2022-05-27 15:02
 */
public class SpiApplication {
    public static void main(String[] args) {
        ServiceLoader<Search> load = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = load.iterator();
        while(iterator.hasNext()){
            Search search = iterator.next();
            search.searchDoc("successful");
        }
    }
}
