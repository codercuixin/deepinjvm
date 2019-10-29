package lecture18;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/22 18:45
 */
public class Test {
    public static void main(String[] args){
        int[] arr = new int[64 * 1024 * 1024];
        //循环1
        long start = System.currentTimeMillis();
        for(int i=0; i<arr.length; i++){
            arr[i] *= 3;
        }
        System.out.println(System.currentTimeMillis() - start);
//        //循环2
//        for(int i=0; i<arr.length; i+=16){
//            arr[i] *= 3;
//        }
//        System.out.println(System.currentTimeMillis() - start);
    }
}
