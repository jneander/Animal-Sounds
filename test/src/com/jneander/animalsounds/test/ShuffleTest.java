package com.jneander.animalsounds.test;

import junit.framework.TestCase;

import com.jneander.animalsounds.util.Shuffle;

public class ShuffleTest extends TestCase {
  private Integer[] array;

  @Override
  protected void setUp() {
    array = new Integer[10];
    for ( int index = 0; index < array.length; index++ )
      array[index] = index;
  }

  public void testShuffleDoesNotDuplicateEntries() {
    Shuffle.shuffle( array );
    assertFalse( hasDuplicates( array ) );
  }

  public < T > boolean hasDuplicates( T[] array ) {
    boolean result = false;

    for ( int firstIndex = 0; firstIndex < array.length - 1; firstIndex++ )
      for ( int secondIndex = firstIndex + 1; secondIndex < array.length; secondIndex++ )
        if ( array[firstIndex] == array[secondIndex] )
          result = true;

    return result;
  }
}