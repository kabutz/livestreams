package session05;

import java.math.*;

public class FactorialRecursive implements Factorial {
  public BigInteger f(int n) {
    if (n == 0) return BigInteger.ONE;
    return BigInteger.valueOf(n).multiply(f(n - 1));
  }
}
