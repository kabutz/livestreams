package session06;

import java.util.*;
import java.util.concurrent.*;

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
    System.out.println(numbers);
    Map<Integer, Collection<String>> invertedNumbers =
        MapInverter.invert(numbers, ConcurrentHashMap::new, TreeSet::new);
    System.out.println("invertedNumbers = " + invertedNumbers);

  }
}
