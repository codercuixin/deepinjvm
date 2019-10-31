package lecture28;

public class LoopPerformanceTest {

  static int foo() {
    int i = 0;
    while (i < 1_000_000_000) {
      i++;
    }
    return i;
  }

  public static void main(String[] args) {
    // warmup
    for (int i = 0; i < 20_000; i++) {
      foo();
    }
    new Exception();
    // measurement
    long current = System.nanoTime();
    for (int i = 1; i <= 10_000; i++) {
      foo();
      if (i % 1000 == 0) {
        long temp = System.nanoTime();
        System.out.println(temp - current);
        current = System.nanoTime();
      }
    }
  }
}