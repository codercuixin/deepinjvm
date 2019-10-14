package lecture12;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/14 9:33
 * todo 复习深入理解JVM，尝试解释课后问题。
 * java -XX:+PrintGC -Xmn100M -XX:PretenureSizeThreshold=10000 lecture12/LifetimeTest > lect
 * ure12/LifetimeTest.gc1
 * java -XX:+PrintHeapAtGC -XX:+PrintGC -Xmn100M -XX:PretenureSizeThreshold=10000 lecture12/
 * LifetimeTest > lecture12/LifetimeTest.gc2
 * java -XX:+PrintHeapAtGC -XX:-UsePSAdaptiveSurvivorSizePolicy -Xmn100M -XX:PretenureSizeTh
 * reshold=10000 lecture12/LifetimeTest > lecture12/LifetimeTest.gc3
 */
public class LifetimeTest {
    private static final int K = 1024;
    private static final int M = K * K;
    private static final int G = K * M;
    private static final int ALIVE_OBJECT_SIZE = 32 * M;

    public static void main(String[] args) {
        int length = ALIVE_OBJECT_SIZE / 64;
        ObjectOf64Bytes[] array = new ObjectOf64Bytes[length];
        for (long i = 0; i < G; i++) {
            array[(int) (i % length)] = new ObjectOf64Bytes();
        }
    }
}

//启用压缩指针 12 + 6*8 + 4（下一个对象用来对齐）
//关闭压缩指针16+ 6*8 = 64
class ObjectOf64Bytes {
    long placeholder0;
    long placeholder1;
    long placeholder2;
    long placeholder3;
    long placeholder4;
    long placeholder5;
}
