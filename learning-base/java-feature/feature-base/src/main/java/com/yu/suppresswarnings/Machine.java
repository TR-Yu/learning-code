package com.yu.suppresswarnings;

import java.util.List;

/**
 * suppresswarnings方法的demo
 *
 * @author YU
 * @date 2022-05-05 22:57
 */
public class Machine {

    @SuppressWarnings({"rawtypes",})
    private final List versions;

    @SuppressWarnings("rawtypes")
    public Machine(List versions) {
        this.versions = versions;
    }

    @SuppressWarnings("unchecked")
    public void addVersion(String version){
        versions.add(version);
    }
}
