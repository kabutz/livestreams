package session05;

import java.math.*;
import java.util.stream.*;

public class FactorialSequentialStream implements Factorial {
  public BigInteger f(int n) {
    return IntStream.rangeClosed(1, n)
        .mapToObj(BigInteger::valueOf)
        .reduce(BigInteger.ONE, BigInteger::multiply);
  }
}
