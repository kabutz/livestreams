package session05;

import java.math.*;

public class FactorialRecursiveDecomposition implements Factorial {
  public BigInteger f(int n) {
    return f(0, n);
  }

  BigInteger f(int from, int to) {
    if (from == to) {
      return from == 0 ? BigInteger.ONE : BigInteger.valueOf(from);
    }
    int mid = (from + to) >>> 1;
    BigInteger b0 = f(from, mid);
    BigInteger b1 = f(mid + 1, to);
    return b0.multiply(b1);
  }
}
