package session02;


import java.util.*;

import static session02.SillyNumberPuzzle.*;

public class OwnSilly5DigitPuzzle {
  public static void main(String... args) {
    int[] correct = {4, 4, 4, 3, 5};

    for (int guess0 = 0; guess0 <= 9; guess0++) {
      for (int guess1 = 0; guess1 <= 9; guess1++) {
        for (int guess2 = 0; guess2 <= 9; guess2++) {
          for (int guess3 = 0; guess3 <= 9; guess3++) {
            for (int guess4 = 0; guess4 <= 9; guess4++) {
              int[] guesses = {guess0, guess1, guess2, guess3, guess4};
              if (true
                  && check(guesses, 1, 1, 6, 5, 0, 3, 2) // 65032 - Two digits are right, one in its place and one in the wrong place
                  && check(guesses, 0, 1, 1, 8, 6, 9, 5) // 18695 - One digits is right, and in its place
                  && check(guesses, 0, 0, 9, 8, 7, 2, 1) // 98721 - All digits are wrong
                  && check(guesses, 2, 0, 1, 3, 5, 7, 9) // 13579 - Two digits are right, but in the wrong place
              ) {
                System.out.println(Arrays.toString(guesses));
              }
            }
          }
        }
      }
    }
  }
}
