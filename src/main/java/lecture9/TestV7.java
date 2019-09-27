package lecture9;

import java.util.function.IntConsumer;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/27 17:40
 */
public class TestV7 {
    public static void target(int i){

    }
    public static void main(String[] args) {
        int x = 2;

        long current = System.currentTimeMillis();
        for (int i = 0; i < 2000000000; i++) {
            if (i % 100000000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
            ((IntConsumer) j->TestV7.target(x+ j)).accept(128);
        }
    }
}
