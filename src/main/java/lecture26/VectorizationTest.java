package lecture26;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/29 15:36
 * java -XX:CompileCommand="dontinline VectorizationTest.foo" -XX:CompileCommand="print Vect
 * orizationTest.foo" -XX:-TieredCompilation  lecture26/VectorizationTest
 */
public class VectorizationTest {
    static void foo(int[] a, int[]b, int[] c){
        for(int i=0; i<a.length; i++){
            c[i] = a[i] + b[i];
        }
    }
    public static void main(String[] args) throws InterruptedException {
        int[] a = new int[]{1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8};
        int[] c= new int[16];
        for(int i = 0 ;i < 20000; i++){
            foo(a,a,c);
        }
        Thread.sleep(2000);
    }
}
