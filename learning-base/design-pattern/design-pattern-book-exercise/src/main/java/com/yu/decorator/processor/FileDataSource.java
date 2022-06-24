package com.yu.decorator.processor;

/**
 * 文件读取
 *
 * @author YU
 * @date 2022-05-04 20:40
 */
public class FileDataSource implements InterfaceDataSource {

    private final String filename;

    public FileDataSource(String filename) {
        this.filename = filename;
    }

    @Override
    public String writeData() {
        System.out.println("将已得到的内存中的数据写入到磁盘中,并返回具体的引用");
        return filename;
    }

    @Override
    public String readData() {
        System.out.println( this.filename + "将磁盘中的数据读取到内存中,并且输出");
        return "adbdddddddddddddddddddd";
    }

    @Override
    public String getData() {
        System.out.println("获取内存中的数据并返回");
        return "adddddddddddddddddddddd";
    }
}
