package lecture17;

import java.util.concurrent.ThreadLocalRandom;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/18 14:43
 * need download hsdis-amd64.dylib  （macos) 放在JDK_HOME/bin目录下
 * hsdis-amd64.dll （windows)
 * hsdis-amd64.so (linux)
 *
 * javac lecture17/CompilationTest.java
 * java -XX:CompileCommand="print,lecture17/CompilationTest.foo"  lecture17/CompilationTest
 *  > lecture17/CompilationTest_compile.output
 *
 */
public class CompilationTest {
    public static int foo(boolean f, int in){
        int v;
        if(f){
            v = in;
        }else{
            v = (int)Math.sin(in);
        }
        if( v == in){
            return 0;
        }else{
            return (int)Math.cos(v);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i< 500000; i++){
            foo(true, 2);
        }
        Thread.sleep(200);
    }
}
