package com.lulu.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * @author zhanglulu on 2020/5/27.
 * for
 */
public class CustomAdviceAdapter extends AdviceAdapter {
    protected CustomAdviceAdapter(MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(Opcodes.ASM5, methodVisitor, access, name, descriptor);
    }
}
