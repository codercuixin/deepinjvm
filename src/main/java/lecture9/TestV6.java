package lecture9;

import java.util.function.IntConsumer;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/27 17:35
 * 测试lambda 与直接调用的性能差距
 */
public class TestV6 {
    public static void target(int i){

    }
    public static void main(String[] args){
        long current = System.currentTimeMillis();
        for(int i=0; i< 2000000000; i++){
            if(i % 100000000 == 0){
                long temp = System.currentTimeMillis();
                System.out.println(temp-current);
                current = temp;
            }
//            ((IntConsumer) j->TestV6.target(j)).accept(128);
            target(128);
        }
    }
}
