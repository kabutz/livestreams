package session05;

import java.math.*;
import java.util.stream.*;

public class FactorialParallelStream implements Factorial {
  public BigInteger f(int n) {
    return IntStream.rangeClosed(1, n)
        .parallel()
        .mapToObj(BigInteger::valueOf)
        .reduce(BigInteger.ONE, BigInteger::multiply);
  }
}
