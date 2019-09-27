package lecture9;

import java.lang.invoke.*;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/27 15:55
 */
public class Circuit {
    public static void startRace(Object obj){
        //aload obj
        //invokedynamic race()
    }
    public static void main(String[] args){
//        startRace(new Horse());
        startRace(new Deer());
    }
    public static CallSite bootstrap(MethodHandles.Lookup l, String name, MethodType callSiteType) throws NoSuchMethodException, IllegalAccessException {
        MethodHandle mh = l.findVirtual(Horse.class, name, MethodType.methodType(void.class));
        return new ConstantCallSite(mh.asType(callSiteType));
    }
}
