package com.yu.decorator.processor;

/**
 * 数据源来源
 *
 * @author YU
 * @date 2022-05-04 20:39
 */
public interface InterfaceDataSource {

    /**
     * 写入数据集
     *
     * @author YU
     * @return @{@link String}
     */
    String writeData();

    /**
     * 读取数据
     *
     * @author YU
     * @return @{@link String}
     */
    String readData();

    /**
     *  文件读取到数据
     *
     * @author YU
     * @return   {@link String}
     */
    String getData();
}
