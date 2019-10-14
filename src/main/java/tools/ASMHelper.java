package tools;

import java.nio.file.*;
import org.objectweb.asm.*;

/**
 * javac -cp asm-all-6.0_BETA.jar tools/ASMHelper.java
 * java -cp "asm-all-6.0_BETA.jar;." tools/ASMHelper
 * todo 更多ASM 可参考 http://web.cs.ucla.edu/~msb/cs239-tutorial/
 * todo 更多JOL http://openjdk.java.net/projects/code-tools/jol/
 */

public class ASMHelper implements Opcodes {

  static class MyMethodVisitor extends MethodVisitor {
    private MethodVisitor mv;
    public MyMethodVisitor(int api, MethodVisitor mv) {
      super(api, null);
      this.mv = mv;
    }

    @Override
    public void visitCode() {
      mv.visitCode();
      mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
      mv.visitLdcInsn("Hello, World!");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
      mv.visitInsn(RETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }
  }

  static class MyClassVisitor extends ClassVisitor {

    public MyClassVisitor(int api, ClassVisitor cv) {
      super(api, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
        String[] exceptions) {
      MethodVisitor visitor = super.visitMethod(access, name, descriptor, signature, exceptions);
      if ("main".equals(name)) {
        return new MyMethodVisitor(ASM6, visitor);
      }
      return visitor;
    }
  }

  public static void main(String[] args) throws Exception {
    ClassReader cr = new ClassReader("tools/Foo3");
    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
    ClassVisitor cv = new MyClassVisitor(ASM6, cw);
    cr.accept(cv, ClassReader.SKIP_FRAMES);
    Files.write(Paths.get("tools/Foo3.class"), cw.toByteArray());
  }
}
