package lecture23;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/28 11:30
 * 需要使用JDK10
 *
 * //被当成是逃逸的，会放到内存里。
 * java -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler -Xlog:gc lecture23/Foo
 */
public class Foo {
    long placeHolder0;
    long placeHolder1;
    long placeHolder2;
    long placeHolder3;
    long placeHolder4;
    long placeHolder5;
    long placeHolder6;
    long placeHolder7;
    long placeHolder8;
    long placeHolder9;
    long placeHoldera;
    long placeHolderb;
    long placeHolderc;
    long placeHolderd;
    long placeHoldere;
    long placeHolderf;

    public static void bar(boolean condition) {
        Foo foo = new Foo();
        if (condition) {
            foo.hashCode();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            bar(i % 100 == 0);
        }
    }

}
