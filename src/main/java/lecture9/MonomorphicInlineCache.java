package lecture9;

import java.lang.invoke.*;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/27 16:19
 */
public class MonomorphicInlineCache {
    private final MethodHandles.Lookup lookup;
    private final String name;

    public MonomorphicInlineCache(MethodHandles.Lookup lookup, String name) {
        this.lookup = lookup;
        this.name = name;
    }

    private Class<?> cachedClass = null;
    private MethodHandle mh = null;

    public void invoke(Object receiver) throws Throwable {
        if (cachedClass != receiver.getClass()) {
            cachedClass = receiver.getClass();
            mh = lookup.findVirtual(cachedClass, name, MethodType.methodType(void.class));
        }
        mh.invoke(receiver);
    }

    public static CallSite bootstrap(MethodHandles.Lookup l, String name, MethodType callSiteType) throws NoSuchMethodException, IllegalAccessException {
        MonomorphicInlineCache ic = new MonomorphicInlineCache(l, name);
        MethodHandle mh = l.findVirtual(MonomorphicInlineCache.class, "invoke", MethodType.methodType(void.class, Object.class));
        return new ConstantCallSite(mh.bindTo(ic));
    }
}
