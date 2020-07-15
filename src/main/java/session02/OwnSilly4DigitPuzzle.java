package session02;


import java.util.*;

import static session02.SillyNumberPuzzle.*;

public class OwnSilly4DigitPuzzle {
  public static void main(String... args) {
    int[] correct = {9, 3, 0, 5};

    for (int guess0 = 0; guess0 <= 9; guess0++) {
      for (int guess1 = 0; guess1 <= 9; guess1++) {
        for (int guess2 = 0; guess2 <= 9; guess2++) {
          for (int guess3 = 0; guess3 <= 9; guess3++) {
            int[] guesses = {guess0, guess1, guess2, guess3};
            if (check(guesses, 1, 0, 3, 4, 1, 2) // 3412 - One digit is right, but in the wrong place
                && check(guesses, 0, 1, 1, 3, 6, 8) // 1368 - One digit is right, and in its place
                && check(guesses, 2, 0, 7, 4, 5, 0) // 7450 - Two digits are right, but in the wrong place
                && check(guesses, 0, 0, 6, 4, 8, 7) // 6487 - All digits are wrong
                && check(guesses, 1, 1, 9, 8, 5, 2) // 9852 - Two digits are right, one in its place and one in the wrong place
            ) {
              System.out.println(Arrays.toString(guesses));
            }
          }
        }
      }
    }
  }

}
