package lecture15;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/16 19:01
 *  编译器多做一点，程序员少做一点。
 *  hashCode冲突时，还会调用equals来判断。
 */
public class SwitchTest {
    public String test(String s) {
        switch (s) {
            case "1":
                return "hi";
            case "2":
                return "hello";
            default:
                return "who are you?";
        }
    }
}
