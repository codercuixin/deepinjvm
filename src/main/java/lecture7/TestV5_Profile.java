package lecture7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/26 19:28
 * -XX:TypeProfileWidth=3, 每个调用可以记录的类型数目，默认值为2，
 */
public class TestV5_Profile {
    public static void target(int i) {

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName("lecture7.TestV5_Profile");
        Method method = clazz.getMethod("target", int.class);
        //关闭权限检查
        method.setAccessible(true);
        polluteProfile();
        Object[] arg = {128};
        long current = System.currentTimeMillis();
        for (int i = 0; i < 2000000000; i++) {
            if (i % 100000000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
            method.invoke(null, arg);
        }
    }

    public static void polluteProfile() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method1 = TestV5_Profile.class.getMethod("target1", int.class);
        Method method2 = TestV5_Profile.class.getMethod("target2", int.class);
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
