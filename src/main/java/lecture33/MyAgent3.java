package lecture33;

import java.lang.instrument.*;
import java.security.ProtectionDomain;

/**
 * javac lecture33/MyAgent3.java
 * jar -cvmf manifest3.txt myagent3.jar lecture33/MyAgent3*
 * java -javaagent:myagent3.jar HelloWorld
 */

public class MyAgent3 {
  public static void premain(String args, Instrumentation instrumentation) {
    instrumentation.addTransformer(new MyTransformer());
  }

  static class MyTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
      System.out.printf("Loaded %s: 0x%X%X%X%X\n", className, classfileBuffer[0], classfileBuffer[1],
          classfileBuffer[2], classfileBuffer[3]);
      return null;
    }
  }
}