package tools;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/14 10:32
 * javac tools/Foo.java
 * javap -p -v tools/Foo > tools/Foo.disasm
 */
public class Foo {
    private int tryBlock;
    private int catchBlock;
    private int finallyBlock;
    private int methodExit;
    public void test(){
        try{
            tryBlock = 0;
        }catch (Exception e){
            catchBlock = 1;
        }finally {
            finallyBlock = 2;
        }
        methodExit = 3;
    }
}
