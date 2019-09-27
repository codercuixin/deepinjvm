package lecture8;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/27 15:21
 * java -XX:+UnlockDiagnosticVMOptions -XX:+ShowHiddenFrames lecture8/Foo2
 */
public class Foo2 {
    public static void bar(Object o){
        new Exception().printStackTrace();
    }
    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup l = MethodHandles.lookup();
        MethodType t = MethodType.methodType(void.class, Object.class);
        MethodHandle mh = l.findStatic(Foo2.class, "bar", t);
        mh.invokeExact(new Object());
    }
}
