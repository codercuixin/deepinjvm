/**
 * * @Author: cuixin
 * * @Date: 2019/10/31 17:08
 * java -javaagent:lecture33/myagent.jar lecture33/HelloWorld
 *
 */
public class HelloWorld {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world");
        Thread.sleep(10000);
        System.out.println("Hello world");
    }

}
