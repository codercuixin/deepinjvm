package lecture15;

import java.util.ArrayList;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/15 13:41
 */
public class Foo {
    public int foo(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        int result = list.get(0);
        return result;
    }
    public int foo1(){
        ArrayList<Integer> list = new ArrayList<>();
        //Javac编译器帮我们做了装箱
        list.add(Integer.valueOf(0));
        //Javac编译器帮我们先做了checkcast（向下转换），然后是拆箱
        int result = ((Integer)list.get(0)).intValue();
        return result;
    }
}
