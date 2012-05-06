package com.jneander.animalsounds.util;

import java.util.Random;

public class Shuffle {
  private static final Random random = new Random();

  public static < T > void shuffle( T[] array ) {
    int swapIndex;
    T temp;

    for ( int index = 0; index < array.length; index++ ) {
      swapIndex = random.nextInt( array.length );

      if ( swapIndex != index ) {
        temp = array[index];
        array[index] = array[swapIndex];
        array[swapIndex] = temp;
      }
    }
  }
}