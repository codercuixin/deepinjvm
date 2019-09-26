package lecture5;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/26 15:35
 * java -XX:CompileCommand='dontinline,*.passThroughImmigration' lecture5/Passenger
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
