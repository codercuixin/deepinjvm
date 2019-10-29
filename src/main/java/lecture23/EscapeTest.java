package lecture23;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/28 10:55
 * java -XX:-DoEscapeAnalysis lecture23/EscapeTest
 */
public class EscapeTest {
    public static void forEach(ArrayList<Object> list, Consumer<Object> f){
        for(Object obj : list){
            f.accept(obj);
        }
    }
    public static void main(String[] args){
        ArrayList<Object> list = new ArrayList<>();
        for(int i = 0 ; i < 100; i++){
            list.add(i);
        }
        for(int i = 0; i< 400000000; i++){
            forEach(list, obj->{});
        }
    }
}
