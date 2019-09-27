package lecture8;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/27 15:36
 */
public class Foo_homework {
    public void bar(Object o) {

    }

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup l = MethodHandles.lookup();
        MethodType t = MethodType.methodType(void.class, Object.class);
        MethodHandle mh = l.findVirtual(Foo_homework.class, "bar", t);

        long current = System.currentTimeMillis();
        for (int i = 1; i <= 2000000000; i++) {

            if (i % 100000000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
            mh.invokeExact(new Foo_homework(), new Object());
        }
    }
}
