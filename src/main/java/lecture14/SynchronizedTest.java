package lecture14;


/**
 * * @Author: cuixin
 * * @Date: 2019/10/14 17:01
 *
 *  Run with -XX:+UnlockDiagnosticVMOptions -XX:+PrintBiasedLockingStatistics -XX:TieredStopAtLevel=1
 *  1.通过参数-XX;+UseBiasedLocking，比较开关偏向锁的输出结果
 *  javac -encoding UTF8  lecture14/SynchronizedTest.java
 *  java -XX:+UseBiasedLocking -XX:+UnlockDiagnosticVMOptions -XX:+PrintBiasedLockingStatistics -XX:TieredStopAtLevel=1 lecture14/SynchronizedTest > lecture14/1_1.txt
 *  java -XX:+UseBiasedLocking -XX:-UnlockDiagnosticVMOptions -XX:+PrintBiasedLockingStatistics -XX:TieredStopAtLevel=1 lecture14/SynchronizedTest > lecture14/1_0.txt
 *2.在main方法的循环前添加lock.hashCode调用，并输出结果
 *  javac -encoding UTF8  lecture14/SynchronizedTest.java
 *  java -XX:+UseBiasedLocking -XX:+UnlockDiagnosticVMOptions -XX:+PrintBiasedLockingStatisti
 * cs -XX:TieredStopAtLevel=1 lecture14/SynchronizedTest > lecture14/2.txt
 *  3.在Lock类中复写hashCode方法，并查看输出结果
 *   *  javac -encoding UTF8  lecture14/SynchronizedTest.java
 *  java -XX:+UseBiasedLocking -XX:+UnlockDiagnosticVMOptions -XX:+PrintBiasedLockingStatisti
 * cs -XX:TieredStopAtLevel=1 lecture14/SynchronizedTest > lecture14/3.txt
 *  4.在main方法的循环前添加System.identityHashCode调用，并查看输出结果。
 * javac -encoding UTF8  lecture14/SynchronizedTest.java
 *  *  java -XX:+UseBiasedLocking -XX:+UnlockDiagnosticVMOptions -XX:+PrintBiasedLockingStatisti
 *  * cs -XX:TieredStopAtLevel=1 lecture14/SynchronizedTest > lecture14/4.txt
 */
public class SynchronizedTest {
    static Lock lock = new Lock();
    static int counter = 0;
    public static void foo(){
        synchronized (lock){
            counter++;
        }
    }
    public static void main(String[] args){
//        lock.hashCode(); //Step2
        System.identityHashCode(lock);//step 4
        for(int i=0; i < 1000000; i++){
            foo();
        }
    }

    static class Lock{
        //Step3
//        @Override
////        public int hashCode() {
////            return 0;
////        }
    }
}
