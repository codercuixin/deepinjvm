package lecture24;
/**
 * * @Author: cuixin
 * * @Date: 2019/10/29 10:55
 * https://www.baeldung.com/graal-java-jit-compiler
 * javac lecture24/CountUpperCase.java
 * java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler lecture24/Co
 * untUpperCase
 * java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Dgraal.PrintCompilation=true lecture24/CountUpperCase > lecture24/CountUpperCase.compileStatistics
 * java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:-UseJVMCICompiler -Dgraal.PrintCompilation=true lecture24/CountUpperCase > lecture24/CountUpperCase.compileStatistics2
 */
public class CountUpperCase {
    static final int ITERATIONS = Math.max(Integer.getInteger("iterations", 1), 1);

    public static void main(String[] args) {
        String sentence = String.join(" ", args);
        for (int iter = 0; iter < ITERATIONS; iter++) {
            if (ITERATIONS != 1) {
                System.out.println("-- iteration " + (iter + 1) + " -- ");
            }
            long total = 0, start = System.currentTimeMillis(), last = start;
            for (int i = 1; i < 10000000; i++) {
                total += sentence.chars().filter(Character::isUpperCase).count();
                if (i % 1000000 == 0) {
                    long now = System.currentTimeMillis();
                    System.out.printf("%d (%d ms) %n", i / 1000000, now - last);
                    last = now;
                }
            }
            System.out.printf("total: %d (%d ms)%n", total, System.currentTimeMillis() - start);
        }
    }
}
