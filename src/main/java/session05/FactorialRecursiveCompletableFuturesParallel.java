package session05;

import java.math.*;
import java.util.concurrent.*;

public class FactorialRecursiveCompletableFuturesParallel implements Factorial {
  public BigInteger f(int n) {
    return f(0, n).join();
  }

  private CompletableFuture<BigInteger> f(int from, int to) {
    if (from == to) {
      return CompletableFuture.completedFuture(
          from == 0 ? BigInteger.ONE : BigInteger.valueOf(from)
      );
    }
    int mid = (from + to) >>> 1;
    return f(from, mid).thenCombineAsync(f(mid + 1, to), BigInteger::multiply);
  }
}
