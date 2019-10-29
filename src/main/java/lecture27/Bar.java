package lecture27;

import java.util.function.IntBinaryOperator;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/29 17:13
 * javac -processor lecture27.AdaptProcessor lecture27/Bar.java
 */
public class Bar {
    @Adapt(IntBinaryOperator.class)
    public static int add(int a, int b){
        return a + b;
    }
}
