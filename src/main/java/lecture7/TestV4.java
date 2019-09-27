package lecture7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/26 19:15
 *在运行java指令中添加如下两个虚拟机参数：
 * -Djava.lang.Integer.IntegerCache.high=128
 * -Dsun.reflect.noInflation=true
 */
public class TestV4 {
    public static void target(int i) {

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName("lecture7.TestV4");
        Method method = clazz.getMethod("target", int.class);
        //关闭权限检查
        method.setAccessible(true);
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
