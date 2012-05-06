package com.jneander.animalsounds.test;

import junit.framework.TestCase;

public class ShuffleTest extends TestCase {
  private Integer[] array;

  @Override
  protected void setUp() {
    array = new Integer[10];
    for ( int index = 0; index < array.length; index++)
      array[index] = index;
  }
}