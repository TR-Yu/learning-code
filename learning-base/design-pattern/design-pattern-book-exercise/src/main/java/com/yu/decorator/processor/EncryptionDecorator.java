package com.yu.decorator.processor;

/**
 * 加解密装饰者
 *
 * @author YU
 * @date 2022-05-04 21:17
 */
public class EncryptionDecorator extends DataSourceDecorator {
    private String data;

    public EncryptionDecorator(InterfaceDataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String writeData() {
        System.out.println("进行加密处理并输出到磁盘");
        return super.writeData();
    }

    @Override
    public String readData() {
        String data = super.readData();
        System.out.println("对数据进行解密" + data);
        return data;
    }

}
