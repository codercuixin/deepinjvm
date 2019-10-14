package test.src.main.java.org.sample;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.IntResult2;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/14 15:27
 * lecture13
 * mvn archetype:generate -DinteractiveMode=false -DarchetypeGroupId=org.openjdk.jcstress -DarchetypeArtifactId=jcstress-java-test-archetype -DarchetypeVersion=0.1.1 -DgroupId=org.sample -DartifactId=test -Dversion=1.0
 * $ cd test
 * $ echo 'package org.sample;
 * import org.openjdk.jcstress.annotations.*;
 * import org.openjdk.jcstress.infra.results.IntResult2;
 * @JCStressTest
 * @Outcome(id = {"0, 0", "0, 2", "1, 0"}, expect = Expect.ACCEPTABLE, desc = "Normal outcome")
 * @Outcome(id = {"1, 2"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "Abnormal outcome")
 * @State
 * public class ConcurrencyTest {
 *   int a=0;
 *   int b=0; // 改成 volatile 试试？
 *   @Actor
 *   public void method1(IntResult2 r) {
 *     r.r2 = a;
 *     b = 1;
 *   }
 *   @Actor
 *   public void method2(IntResult2 r) {
 *     r.r1 = b;
 *     a = 2;
 *   }
 * }' > src/main/java/org/sample/ConcurrencyTest.java
 * $ mvn package
 * $ java -jar target/jcstress.jar
 * https://wiki.openjdk.java.net/display/CodeTools/jcstress
 * todo 这玩意真牛逼，可以复现即时编译器出现的重排序。
 * 还可以重现非安全发布。
 * todo:
 * http://vlkan.com/blog/post/2014/02/14/java-safe-publication/
 * http://hg.openjdk.java.net/code-tools/jcstress/file/64f2cf32fa0a/tests-custom/src/main/java/org/openjdk/jcstress/tests/unsafe/UnsafePublication.java
 */

@JCStressTest
@Outcome(id = {"0, 0", "0, 2", "1, 0"}, expect = Expect.ACCEPTABLE, desc = "Normal outcome")
@Outcome(id = {"1, 2"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "Abnormal outcome")
@State
public class ConcurrencyTest {
    int a=0;
    int b=0; // 改成 volatile 试试？
    @Actor
    public void method1(IntResult2 r) {
        r.r2 = a;
        b = 1;
    }
    @Actor
    public void method2(IntResult2 r) {
        r.r1 = b;
        a = 2;
    }
}
