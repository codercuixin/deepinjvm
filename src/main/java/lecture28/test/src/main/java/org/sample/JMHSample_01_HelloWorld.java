package test.src.main.java.org.sample;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/30 13:30
 *   mvn clean install
 *  java -jar target/benchmarks.jar JMHSample_01
 */
public class JMHSample_01_HelloWorld {
    @Benchmark
    public void wellHelloThere(){

    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().
                include(JMHSample_01_HelloWorld.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
