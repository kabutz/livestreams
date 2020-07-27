package session05;

import java.math.*;

public class FactorialPuzzle {
  private static final Factorial factorial = new FactorialParallelStream();

  public static void main(String... args) {
    for (int i = 0; i < 10; i++) {
      test();
    }
  }

  private static void test() {
    long time = System.nanoTime();
    try {
      BigInteger fact200k = factorial.f(200_000);
      System.out.println("fact200k.bitLength() = " + fact200k.bitLength());
      if (fact200k.bitLength() != 3521929)
        throw new AssertionError("Incorrect result");
    } finally {
      time = System.nanoTime() - time;
      System.out.printf("time = %dms%n", (time / 1_000_000));
    }
  }
}
