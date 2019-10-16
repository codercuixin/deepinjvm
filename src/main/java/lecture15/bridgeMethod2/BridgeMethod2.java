package lecture15.bridgeMethod2;


/**
 * * @Author: cuixin
 * * @Date: 2019/10/16 18:45
 * javac lecture15/bridgeMethod2/BridgeMethod2.java
 * javap -p -v lecture15/bridgeMethod2/BridgeMethod2 > lecture15/bridgeMethod2/BridgeMethod2
 * .jasm
 */
public class BridgeMethod2{

}
class Merchant {
    public Number actionPrice(Customer customer) {
        return 0;
    }
}
class NaiveMerchant extends Merchant{
    @Override
    public Double actionPrice(Customer customer) {
        return 0.0D;
    }
}
interface Customer{

}

