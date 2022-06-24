package com.yu.decorator.processor;

/**
 * 装饰者模式
 *
 * @author YU
 * @date 2022-05-04 21:15
 */
public class DataSourceDecorator implements InterfaceDataSource {
    protected InterfaceDataSource wrapper;

    public DataSourceDecorator(InterfaceDataSource dataSource) {
        this.wrapper = dataSource;
    }

    @Override
    public String writeData() {
        return wrapper.writeData();
    }

    @Override
    public String readData() {
        return wrapper.readData();
    }

    @Override
    public String getData() {
        return wrapper.getData();
    }
}
