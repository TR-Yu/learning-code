package com.yu;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.AdviceAdapter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.ASM9;

/**
 * 类似于aop的实现
 * 需要对MyMethod类进行增强，在前面增加start, end 输出
 *
 * @author YU
 * @date 2022-05-30 1:01
 */
public class AopTest extends ClassLoader {

    public static void enhanceMyMethod() throws IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ClassReader cr = new ClassReader(MyMethod.class.getName());
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);

        {
            MethodVisitor methodVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(Opcodes.RETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }

        ClassVisitor cv = new ProfilingClassAdapter(cw, MyMethod.class.getSimpleName());
        cr.accept(cv, ClassReader.EXPAND_FRAMES);

        byte[] bytes = cw.toByteArray();
        GenerateTest.outFile(bytes,"MyMethod.class");

        // 使用反射机制调用方法
        Class<?> clazz = new AopTest().defineClass("com.yu.MyMethod", bytes, 0, bytes.length);
        Method queryUserInfo = clazz.getMethod("queryUserInfo", String.class);
        Object obj = queryUserInfo.invoke(clazz.newInstance(), "10001");
        System.out.println("测试结果：" + obj);
    }

    static class ProfilingClassAdapter extends ClassVisitor {

        public ProfilingClassAdapter(final ClassVisitor cv, String innerClassName) {
            super(ASM9, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access,
                                         String name,
                                         String desc,
                                         String signature,
                                         String[] exceptions) {
            System.out.println("access：" + access);
            System.out.println("name：" + name);
            System.out.println("desc：" + desc);

            if (!"queryUserInfo".equals(name)) {
                return null;
            }

            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

            return new ProfilingMethodVisitor(mv, access, name, desc);
        }

    }

    static class ProfilingMethodVisitor extends AdviceAdapter {

        private String methodName = "";

        protected ProfilingMethodVisitor(MethodVisitor methodVisitor, int access, String name, String descriptor) {
            super(ASM9, methodVisitor, access, name, descriptor);
            this.methodName = name;
        }

        @Override
        protected void onMethodEnter() {
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
            mv.visitVarInsn(LSTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
        }

        @Override
        protected void onMethodExit(int opcode) {
            if ((IRETURN <= opcode && opcode <= RETURN) || opcode == ATHROW) {
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

                mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
                mv.visitInsn(DUP);
                mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
                mv.visitLdcInsn("方法执行耗时(纳秒)->" + methodName + "：");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);

                mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
                mv.visitVarInsn(LLOAD, 2);
                mv.visitInsn(LSUB);

                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

            }
        }
    }

    public static void runProxyMethod() throws InstantiationException, IllegalAccessException, IOException, InvocationTargetException, NoSuchMethodException {
        enhanceMyMethod();

    }
}
