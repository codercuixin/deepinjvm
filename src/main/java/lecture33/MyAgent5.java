package lecture33;

import java.lang.instrument.*;
import java.security.ProtectionDomain;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

/**
 * javac lecture33/MyProfiler.java
 * javac -cp .;asm-7.2.jar;asm-tree-7.2.jar lecture33/MyAgent5.java
 * jar -cvmf manifest5.txt myagent5.jar lecture33/*.class
 * java -javaagent:myagent5.jar -cp .;asm-7.2.jar;asm-tree-7.2.jar HelloWorld
 */
public class MyAgent5 {

  public static void premain(String args, Instrumentation instrumentation) {
    instrumentation.addTransformer(new MyTransformer());
  }

  static class MyTransformer implements ClassFileTransformer, Opcodes {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
      if (className.startsWith("java")    ||
          className.startsWith("javax")   || 
          className.startsWith("jdk")     ||
          className.startsWith("sun")     ||
          className.startsWith("com/sun") ||
          className.startsWith("lecture33")) {
        // Skip JDK classes and profiler classes
        return null;
      }

      ClassReader cr = new ClassReader(classfileBuffer);
      ClassNode classNode = new ClassNode(ASM7);
      cr.accept(classNode, ClassReader.SKIP_FRAMES);

      for (MethodNode methodNode : classNode.methods) {
        for (AbstractInsnNode node : methodNode.instructions.toArray()) {
          if (node.getOpcode() == NEW) {
            TypeInsnNode typeInsnNode = (TypeInsnNode) node;

            InsnList instrumentation = new InsnList();
            instrumentation.add(new LdcInsnNode(Type.getObjectType(typeInsnNode.desc)));
            instrumentation.add(new MethodInsnNode(INVOKESTATIC, "lecture33.MyProfiler", "fireAllocationEvent",
                "(Ljava/lang/Class;)V", false));

            methodNode.instructions.insert(node, instrumentation);
          }
        }
      }

      ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
      classNode.accept(cw);
      return cw.toByteArray();
    }
  }

}