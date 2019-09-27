package lecture7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/26 18:48
 * -XX:+PrintGC查看GC日志
 * 默认Integer缓存[-128,127], -Djava.lang.Integer.IntegerCache.high=128, 或者自己额外缓存128
 *
 */
public class TestV2 {
    public static void target(int i) {

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName("lecture7.TestV2");
        Method method = clazz.getMethod("target", int.class);
        long current = System.currentTimeMillis();
        for (int i = 0; i < 2000000000; i++) {
            if( i % 100000000 == 0){
                long temp = System.currentTimeMillis();
                System.out.println(temp-current);
                current = temp;
            }
            method.invoke(null, 128);
        }
    }
}
