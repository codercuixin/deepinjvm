package lecture33;

import java.lang.instrument.*;
import java.security.ProtectionDomain;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

/**
 * windows use ; linux use :
 * javac -cp .;asm-7.2.jar;asm-tree-7.2.jar lecture33/MyAgent4.java
 * jar -cvmf manifest4.txt myagent4.jar lecture33/MyAgent4*.class
 * java -javaagent:myagent4.jar -cp .;asm-7.2.jar;asm-tree-7.2.jar  HelloWorld
 */

public class MyAgent4 {
  public static void premain(String args, Instrumentation instrumentation) {
    instrumentation.addTransformer(new MyTransformer());
  }

  static class MyTransformer implements ClassFileTransformer, Opcodes {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
      ClassReader cr = new ClassReader(classfileBuffer);
      ClassNode classNode = new ClassNode(ASM7);
      cr.accept(classNode, ClassReader.SKIP_FRAMES);

      for (MethodNode methodNode : classNode.methods) {
        if ("main".equals(methodNode.name)) {
          InsnList instrumentation = new InsnList();
          instrumentation.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
          instrumentation.add(new LdcInsnNode("Hello, Instrumentation!"));
          instrumentation
              .add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false));

          methodNode.instructions.insert(instrumentation);
        }
      }

      ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
      classNode.accept(cw);
      return cw.toByteArray();
    }
  }
}