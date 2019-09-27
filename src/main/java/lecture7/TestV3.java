package lecture7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/26 19:07
 * 使用数组替换可变参数来提升性能。
 */
public class TestV3 {
    public static void target(int i) {

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName("lecture7.TestV3");
        Method method = clazz.getMethod("target", int.class);
        Object[] arg = new Object[1];
        arg[0] = 128;
        long current = System.currentTimeMillis();
        for (int i = 0; i < 2000000000; i++) {
            if( i % 100000000 == 0){
                long temp = System.currentTimeMillis();
                System.out.println(temp-current);
                current = temp;
            }
            method.invoke(null, arg);
        }
    }
}
