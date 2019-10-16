package lecture15;

import java.util.ArrayList;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/16 18:57
 * 查看foreach的语法糖
 */
public class ForeachTest {
    public void foo(int[] array){
        for(int item: array){

        }
    }
    public void foo(ArrayList<Integer> list){
        for(Integer item: list){

        }
    }
}
