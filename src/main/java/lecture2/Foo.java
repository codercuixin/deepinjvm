package lecture2;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/25 19:37
 * //todo https://gist.github.com/qudongfang/49635d86882c03e49cff2b0f7d833805
 */
public class Foo {
    private boolean flag;

    public boolean getFlag() {
        return flag;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        Unsafe unsafe = getUnsafeByConstructor();
        Foo foo = new Foo();
        foo.flag = true;
        Field field = foo.getClass().getDeclaredField("flag");
        long offset = unsafe.objectFieldOffset(field);
        unsafe.putInt(foo, offset, 2);
        if (foo.flag) {
            System.out.println("Hello, Java foo.flag");
        }
        if (foo.flag == true) {
            System.out.println("Hello, Java foo.flag == true");
        }
        if (foo.getFlag()) {
            System.out.println("Hello, Java");
        }
        if (foo.getFlag() == true) {
            System.out.println("Hello, JVM");
        }
    }

    public static Unsafe getUnsafeByField() throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }

    public static Unsafe getUnsafeByConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }
}
