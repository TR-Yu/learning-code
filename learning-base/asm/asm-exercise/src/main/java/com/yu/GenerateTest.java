package com.yu;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author YU
 * @date 2022-05-30 0:23
 */
public class GenerateTest {
    private static byte[] generate() {
        ClassWriter classWriter = new ClassWriter(0);
        // 定义对象头；版本号、修饰符、全类名、签名、父类、实现的接口
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "com/yu/Base", null, "java/lang/Object", null);
        // 添加方法；修饰符、方法名、描述符、签名、异常
        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        // 执行指令；获取静态属性
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        // 加载常量 load constant
        methodVisitor.visitLdcInsn("Hello World");
        // 调用方法
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        // 返回
        methodVisitor.visitInsn(Opcodes.RETURN);
        // 设置操作数栈的深度和局部变量的大小
        methodVisitor.visitMaxs(2, 1);
        // 方法结束
        methodVisitor.visitEnd();
        // 类完成
        classWriter.visitEnd();
        // 生成字节数组
        return classWriter.toByteArray();
    }

    public static void generateClazz() {
        // 通过ASM生成 .class 字节码
        byte[] generate = GenerateTest.generate();
        outFile(generate,"Base.class");
    }

    public static void outFile(byte[] bytes, String fileName) {
        assert fileName != null;
        File file = new File(".\\asm-base\\target\\classes\\com\\yu\\" + fileName);
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(file);
            fileOut.write(bytes);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileOut != null;
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
