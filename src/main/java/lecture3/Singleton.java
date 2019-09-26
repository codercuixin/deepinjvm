package lecture3;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/25 20:23
 */
public class Singleton {
    private Singleton(){}
    private static class LazyHolder{
        static final Singleton INSTANCE = new Singleton();
        static{
            System.out.println("LazyHolder.<clinit>");
        }
    }

    public static Object getInstance(boolean flag){
        if(flag) return new LazyHolder[2];
        return LazyHolder.INSTANCE;
    }

    public static void main(String[] args){
        getInstance(true);
        System.out.println("-----");
        getInstance(false);
    }
}
