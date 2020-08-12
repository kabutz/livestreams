package session06;

import java.util.*;

public class MapInverterDemo {
  public static void main(String[] args) {
    Map<String, Integer> numbers =
        Map.of(
            "one", 1,
            "eins", 1,
            "uno", 1,
            "two", 2,
            "six", 6,
            "sechs", 6,
            "life", 42
        );
    Map<Integer, List<String>> invertedNumbers =
        MapInverter.invert(numbers);
    System.out.println("invertedNumbers = " + invertedNumbers);
  }
}
