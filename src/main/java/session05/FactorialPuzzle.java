package session05;

import java.math.*;
import java.util.stream.*;

public class FactorialPuzzle {
  public static void main(String... args) {
    for (int i = 0; i < 10; i++) {
      test();
    }
  }

  private static void test() {
    long time = System.nanoTime();
    try {
      BigInteger fact200k = factorial(200_000);
      System.out.println("fact200k.bitLength() = " + fact200k.bitLength());
    } finally {
      time = System.nanoTime() - time;
      System.out.printf("time = %dms%n", (time / 1_000_000));
    }
  }

  private static BigInteger factorial(int n) {
    return
        IntStream.rangeClosed(1, n)
            .mapToObj(BigInteger::valueOf)
            .parallel()
            .reduce(BigInteger.ONE, BigInteger::multiply);
  }
}
