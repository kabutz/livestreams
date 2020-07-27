package session05;

import java.math.*;
import java.util.concurrent.*;

public class FactorialRecursiveDecompositionParallel implements Factorial {
  public BigInteger f(int n) {
    return f(0, n);
  }

  private BigInteger f(int from, int to) {
    if (from == to) {
      return from == 0 ? BigInteger.ONE : BigInteger.valueOf(from);
    }
    int mid = (from + to) >>> 1;
    RecursiveTask<BigInteger> b0Task = new RecursiveTask<BigInteger>() {
      protected BigInteger compute() {
        return f(from, mid);
      }
    };
    b0Task.fork();
    BigInteger b1 = f(mid + 1, to);
    BigInteger b0 = b0Task.join();
    return b0.multiply(b1);
  }
}
