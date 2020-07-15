package session03;

import java.net.*;
import java.nio.file.*;

public class DigitSearcher {
  public static void main(String... args) throws Exception {
    URL resource = DigitSearcher.class.getClassLoader().getResource(
        "session03/pi1000000.txt"
    );
    char[] allChars = Files.readString(Path.of(resource.toURI())).toCharArray();
    for (int i = 0; i < allChars.length; i++) {
      allChars[i] -= '0';
    }

    for (int i = 0; i < 10; i++) {
      search(allChars);
    }
  }

  private static void search(char[] allChars) {
    int[] count;
    long time = System.nanoTime();
    try {
      count = new int[100_000];

      int number = 0;
      for (int i = 0; i < allChars.length; i++) {
        number *= 10;
        number += allChars[i];
        number %= 100_000;
        if (i > 3) {
          count[number]++;
        }
      }

    } finally {
      time = System.nanoTime() - time;
      System.out.printf("time = %dms%n", (time / 1_000_000));
    }
    for (int i = 0; i < count.length; i++) {
      if (count[i] == 0) {
        System.out.printf("%05d%n", i);
      }
    }
  }
}
