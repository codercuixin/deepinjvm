package lecture4;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/26 9:34
 */
public class VIPOnlyMerchant extends NormalMerchant<VIP> {
    @Override
    public double actionPrice(double price, VIP customer) {
        return 0.0;
    }
}
