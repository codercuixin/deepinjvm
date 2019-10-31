package lecture32;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/31 13:31
 * * * 该命令仅适用于macOS
 *   * gcc -I$JAVA_HOME/include -I$JAVA_HOME/include/linux -fPIC -o libfoo.so -shared foo.c
 *   *
 *   # 该命令仅适用于macOS
 *   $ gcc -I$JAVA_HOME/include -I$JAVA_HOME/include/darwin -o libfoo.dylib -shared foo.c
 *
 *   System.load和System.loadLibrary可以互相替换
 *   java -Djava.library.path=lecture32/ lecture32/Foo
 *
 *
 */
public class Foo {
    public static native void foo();
    public native void bar(int i, long j);
    public native void bar(String s, Object o);


    public static void main(String[] args) {
        try {
            // System.load("/full-path/libfoo.so");
            System.load("foo");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            System.exit(1);
        }
        new Foo().bar("", "");
    }
}
