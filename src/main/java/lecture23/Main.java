package lecture23;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/29 10:06
 * https://zh.wikipedia.org/wiki/%E9%80%83%E9%80%B8%E5%88%86%E6%9E%90
 * 逃逸分析，将堆分配转换为栈分配
 * 在这个示例中，创建了两个对象(用alloc注释)，其中一个作为方法的参数。
 * 方法setFoo()接收到foo参数后，保存Foo对象的引用。如果Bar对象保存在堆中，
 * 那么Foo的引用将逃逸。但在这种情况下，编译器可以使用逃逸分析确定Bar对象本身并没有逃逸example()的调用。
 * 这意味着Foo引用无法逃逸。因此，编译器可以安全地在栈上分配两个对象。
 */
public class Main {
    public static void main(String[] args){
        example();
    }
    public static void example(){
        Foo1 foo1 = new Foo1();
        Bar bar = new Bar();
        bar.setFoo1(foo1);
    }
}
class Foo1{}

class Bar{
    private Foo1 foo1;

    public void setFoo1(Foo1 foo1) {
        this.foo1 = foo1;
    }
}
