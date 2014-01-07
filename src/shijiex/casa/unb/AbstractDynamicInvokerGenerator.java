/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shijiex.casa.unb;

import org.objectweb.asm.*;
import java.lang.invoke.*;

/**
 *
 * @author shijiex
 */
public abstract class AbstractDynamicInvokerGenerator implements Opcodes {

    public byte[] dump(String dynamicInvokerClassName, String dynamicLinkageClassName, String bootstrapMethodName, String targetMethodDescriptor)
            throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, dynamicInvokerClassName, null, "java/lang/Object", null);

        {
        	//For object <init> section
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
        	//For main sections
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            mv.visitLdcInsn("xu");
            mv.visitLdcInsn("shijie");
            MethodType mt = MethodType.methodType(CallSite.class, MethodHandles.Lookup.class, String.class,
                    MethodType.class);

            Handle bootstrap = new Handle(Opcodes.H_INVOKESTATIC, dynamicLinkageClassName, bootstrapMethodName,
                    mt.toMethodDescriptorString());
            int maxStackSize = addMethodParameters(mv);
            mv.visitInvokeDynamicInsn("helloWorld", targetMethodDescriptor, bootstrap);
            // NOTICE: Adjust visitMaxs if added below two: Another BootStrap methods
            //Handle newBootstrap = new Handle(Opcodes.H_INVOKESTATIC, dynamicLinkageClassName, "getStringBootstrap", mt.toMethodDescriptorString());
            //mv.visitInvokeDynamicInsn("getString", "(Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/String;", newBootstrap);
            

            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }

    protected abstract int addMethodParameters(MethodVisitor mv);
}
