package tools;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/14 13:33
 * java -cp asm-all-6.0_BETA.jar org.objectweb.asm.util.ASMifier tools/Foo3.class >  tools/Foo3Dum
 * p.java
 * javac -cp asm-all-6.0_BETA.jar asm/tools/Foo3Dump.java tools/Foo3Wrapper.java
 * java -cp asm-all-6.0_BETA.jar;. tools/Foo3Wrapper
 */
public class Foo3 {
    public static void main(String[] args) {
        boolean flag = true;
        if (flag) {
            System.out.println("Hello,Java!");
        }
        if (flag == true) {
            System.out.println("Hello, JVM!");
        }
    }
}
