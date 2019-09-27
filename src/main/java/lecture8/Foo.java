package lecture8;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/27 14:59
 */
public class Foo {
    private static void bar(Object o){

    }
    public static MethodHandles.Lookup lookup(){
        return MethodHandles.lookup();
    }
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException {
        MethodHandles.Lookup l = Foo.lookup();
        Method m = Foo.class.getDeclaredMethod("bar", Object.class);
        MethodHandle mh0 = l.unreflect(m);

        MethodType t = MethodType.methodType(void.class, Object.class);
        MethodHandle mh1 = l.findStatic(Foo.class, "bar", t);
    }
    public void test(MethodHandle mh, String s) throws Throwable {
        mh.invokeExact(s);
        mh.invokeExact((Object)s);
    }
}
