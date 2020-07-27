package session05;

import java.math.*;

public class FactorialIterative implements Factorial {
  public BigInteger f(int n) {
    BigInteger result = BigInteger.ONE;
    for (int i = 1; i <= n; i++) {
      result = result.multiply(BigInteger.valueOf(i));
    }
    return result;
  }
}
