package com.yu.spi;

import java.util.List;

/**
 * 查找接口
 *
 * @author YU
 * @date 2022-05-27 14:52
 */
public interface Search {

    /**
     * 关键词搜索接口
     *
     * @author YU
     * @param keyword  {@link String}
     * @return   {@link List< String>}
     */
    public List<String> searchDoc(String keyword);
}
