package session02;


import java.util.*;

// github.com/kabutz/livestreams
/*
147 - One digit is right, but in the wrong place (1 white)
189 - One digit is right, and in it's place (1 red)
964 - Two digits are correct, but both are in the wrong place (2 whites)
523 - All digits are wrong (0 whites)
286 - One digit is right, but in the wrong place (1 white)
 */
public class SillyNumberPuzzle {
  public static void main(String... args) {
    for (int guess0 = 0; guess0 <= 9; guess0++) {
      for (int guess1 = 0; guess1 <= 9; guess1++) {
        for (int guess2 = 0; guess2 <= 9; guess2++) {
          int[] guesses = {guess0, guess1, guess2};
          if (check(guesses, 1, 0, 1, 4, 7)
              && check(guesses, 0, 1, 1, 8, 9)
              && check(guesses, 2, 0, 9, 6, 4)
              && check(guesses, 0, 0, 5, 2, 3)
              && check(guesses, 1, 0, 2, 8, 6)) {
            System.out.println(Arrays.toString(guesses));
          }
        }
      }
    }
  }

  public static boolean check(int[] guesses, int whites, int reds, int... previous) {
    for (int i = 0; i < guesses.length; i++) {
      for (int j = 0; j < previous.length; j++) {
        if (guesses[i] == previous[j]) {
          if (i == j) reds--;
          else whites--;
        }
      }
    }
    return whites == 0 && reds == 0;
  }
}
