package session05;

import java.lang.management.*;
import java.math.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class FactorialPuzzle {
  private static final Factorial factorial =
      new FactorialRecursiveCompletableFuturesParallel();

  public static void main(String... args) {
    System.out.println(factorial.getClass());
    long elapsedTime = System.nanoTime();
    try {
      for (int i = 0; i < 10; i++) {
        test();
      }
    } finally {
      elapsedTime = System.nanoTime() - elapsedTime;
      System.out.printf("elapsedTime = %dms%n", (elapsedTime / 1_000_000));
    }
    ThreadMXBean tmb = ManagementFactory.getThreadMXBean();
    Set<Thread> workerThreads =
        IntStream.range(0, ForkJoinPool.getCommonPoolParallelism() * 2)
            .parallel()
            .mapToObj(i -> Thread.currentThread())
            .collect(Collectors.toSet());
    long totalUserTime = workerThreads.stream()
        .mapToLong(Thread::getId)
        .map(tmb::getThreadUserTime)
        .sum();

    System.out.printf("elapsed=%d, user=%d, parallelism=%.2f%n",
        elapsedTime, totalUserTime, ((double) totalUserTime ) / elapsedTime);
  }

  private static void test() {
    long time = System.nanoTime();
    try {
      BigInteger fact200k = factorial.f(200_000);
      System.out.println("fact200k.bitLength() = " + fact200k.bitLength());
      if (fact200k.bitLength() != 3233400)
        throw new AssertionError("Incorrect result");
    } finally {
      time = System.nanoTime() - time;
      System.out.printf("time = %dms%n", (time / 1_000_000));
    }
  }
}
