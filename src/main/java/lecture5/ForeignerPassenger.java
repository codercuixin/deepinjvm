package lecture5;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/26 15:38
 * java -XX:CompileCommand='dontinline,*.passThroughImmigration'
 *
 * java -XX:CompileCommand='dontinline,*.passThroughImmigration' Passenger
 *
  */
public class ForeignerPassenger extends Passenger {
    @Override
    void passThroughImmigration() {
    }
}
