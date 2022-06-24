package com.yu;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author YU
 * @date 2022-05-30 0:22
 */
public class AsmApplication {
    public static void main(String[] args) {

        // ASM框架生成字节码演示
        GenerateTest.generateClazz();

        // ASM框架修改字节码演示
        try {
            AopTest.runProxyMethod();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
