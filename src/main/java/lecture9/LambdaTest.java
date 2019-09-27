package lecture9;

import java.util.stream.IntStream;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/27 16:58
 *
 * 下面的语句，会在lecture9下面生成两个LambdaTest$$Lambda$1.class，LambdaTest$$Lambda$2.class
 *java -Djdk.internal.lambda.dumpProxyClasses=.  lecture9/LambdaTest
 */
public class LambdaTest {
    public static void main(String[] args){
        int x = 1;
        IntStream.of(1,2,3).map(i -> i * 2).map(i -> i *x);
    }
}
