package session05;

import java.math.*;
import java.util.concurrent.*;

public class FactorialRecursiveCompletableFuturesParallelHybrid implements Factorial {
  public BigInteger f(int n) {
    return f(0, n).join();
  }

  private static final FactorialRecursiveDecomposition singleThreaded =
      new FactorialRecursiveDecomposition();
  private static final int THRESHOLD = 10_000;

  private CompletableFuture<BigInteger> f(int from, int to) {
    if (to - from < THRESHOLD) {
      return CompletableFuture.completedFuture(
          singleThreaded.f(from, to)
      );
    }
    int mid = (from + to) >>> 1;
    return f(from, mid).thenCombineAsync(f(mid + 1, to), BigInteger::multiply);
  }
}
