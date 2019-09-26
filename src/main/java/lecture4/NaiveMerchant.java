package lecture4;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/26 8:56
 */
public class NaiveMerchant extends Merchant {
    @Override
    public Double actionPrice(double price, Customer customer) {
        return 1.0;
    }
}
