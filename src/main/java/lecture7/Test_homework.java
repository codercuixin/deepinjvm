package lecture7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/26 19:37
 *  -XX:TypeProfileWidth=3
 */
public class Test_homework {
    public static void target(int i) {

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName("lecture7.Test_homework");
        Method method = clazz.getMethod("target", int.class);
        //关闭权限检查
        method.setAccessible(true);
        polluteProfile();
        long current = System.currentTimeMillis();
        for (int i = 0; i < 2000000000; i++) {
            if (i % 100000000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
            method.invoke(null, 128);
        }
    }

    public static void polluteProfile() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method1 = Test_homework.class.getMethod("target", int.class);
        Method method2 = Test_homework.class.getMethod("target", int.class);
        System.out.println("method1 == method2 is "+(method1 == method2));
        System.out.println("method1.equals(method2) is "+method1.equals(method2));
        for (int i = 0; i < 2000; i++) {
            method1.invoke(null, 0);
            method2.invoke(null, 0);
        }
    }

    public static void target1(int i) {

    }

    public static void target2(int i) {

    }
}
