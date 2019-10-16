package lecture15;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/16 18:26
 * javac lecture15/BridgeMethod.java
 * javap -p -v lecture15/Merchant > lecture15/Merchant.jasm
 * javap -p -v lecture15/VIPOnlyMerchant > lecture15/VIPOnlyMerchant.jasm
 */
public class BridgeMethod {

}
interface Customer {
}
interface VIP extends Customer{

}

class Merchant<T extends Customer> {
    public double actionPrice(T customer) {
        return 0.0d;
    }
}
class VIPOnlyMerchant extends Merchant<VIP>{
    @Override
    public double actionPrice(VIP customer) {
        return 0.0d;
    }

//    //生成的桥接方法等价于
//    public double actionPrice(Customer customer) {
//        return actionPrice(VIP(customer));
//    }
}

