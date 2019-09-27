package lecture5;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/26 15:35
 * java -XX:CompileCommand='dontinline,*.passThroughImmigration' lecture5/Passenger
 * 由于这里前一半是中国旅客，后一半是外国游客，因而JVM的单态内联可以使用内联缓存，性能也比不内联提高了许多。
 */
public abstract class Passenger {
    /**
     * 通过
     */
    abstract void passThroughImmigration();
    public static void main(String[] args){
        Passenger a = new ChinesePassenger();
        Passenger b = new ForeignerPassenger();
        long current = System.currentTimeMillis();
        for(int i= 1; i< 2000000000;i++){
            if(i % 100000000 ==0){
                long temp  = System.currentTimeMillis();
                System.out.println(temp -current);
                current = temp;
            }
            Passenger c = (i <1000000000) ? a:b;
            c.passThroughImmigration();
        }
    }
}
