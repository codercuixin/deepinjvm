package lecture6;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/26 16:17
 * //todo 字节码指令需要熟悉，深入理解jvm书籍重看。
 */
public class Foo {
    private int tryBlock;
    private int catchBlock;
    private int finallyBlock;
    private int methodBlock;
    public void test(){
        for(int i=0; i< 100; i++){
            try{
                tryBlock = 0;
                if(i < 50){
                    continue;
                }else if( i < 80){
                    break;
                }else{
                    return;
                }
            }catch (Exception e){
                catchBlock = 1;
            }finally {
                finallyBlock = 2;
            }
        }
        methodBlock = 3;
    }
}
