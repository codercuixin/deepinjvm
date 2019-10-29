package lecture25;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/29 14:35
 */
public class Foo {
    int foo(int x, int y, int[] a){
        int sum = 0;
        for(int i=0; i<a.length; i++){
            sum += x * y + a[i];
        }
        return sum;
    }
}
