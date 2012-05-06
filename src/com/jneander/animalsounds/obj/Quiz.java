package com.jneander.animalsounds.obj;

import java.util.Random;

import android.content.Context;

import com.jneander.animalsounds.util.DatabaseAccessor;

public class Quiz {
  private Animal[] animals;
  private Integer[] randomIndices;
  private int currentAnimalIndex = 0;

  private Random random = new Random();
  private final int MAX_CHOICES = 4;

  private Animal[] choices = new Animal[MAX_CHOICES];

  public Quiz( Context context ) {
    DatabaseAccessor dbAccessor = new DatabaseAccessor( context );

    animals = dbAccessor.getAnimalsArray();
    shuffle( animals );

    randomIndices = new Integer[animals.length];
  }

  public void selectNextAnimal() {
    currentAnimalIndex = (currentAnimalIndex + 1) % animals.length;
    shuffle( randomIndices );
  }

  public Animal[] getChoices() {
    choices[0] = animals[currentAnimalIndex];
    int randomIndex = 0;

    for ( int choiceIndex = 1; choiceIndex < MAX_CHOICES; choiceIndex++ ) {
      if ( randomIndices[randomIndex] == currentAnimalIndex )
        randomIndex++;
      choices[choiceIndex] = animals[randomIndices[randomIndex]];
    }

    shuffle( choices );

    return choices;
  }

  public Animal getAnswer() {
    return animals[currentAnimalIndex];
  }

  private < T > void shuffle( T[] array ) {
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