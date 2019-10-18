package lecture17;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/18 15:13
 * todo 使用GraalVM 验证。
 * javac -encoding UTF* lecture17/CompilationTest2.java
 * java -XX:+UnlockExperimentalVMOptions -XX:+UseJVMC1Compiler -XX:CompileCommand="print,lecture17/CompilationTest2.hash"  lecture17/CompilationTest2  > lecture17/CompilationTest2_compile.output
 */
public class CompilationTest2 {
    public static int hash(Object input){
        if(input instanceof Exception){
            return System.identityHashCode(input);
        }else{
            return input.hashCode();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i< 500000; i++){
            hash(i);
        }
        Thread.sleep(2000);
    }
}
