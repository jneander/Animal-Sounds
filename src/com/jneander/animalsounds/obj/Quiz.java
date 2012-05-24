package com.jneander.animalsounds.obj;

import android.content.Context;

import com.jneander.animalsounds.util.AnimalLoader;
import com.jneander.animalsounds.util.Shuffle;

public class Quiz {
  private Animal[] animals;
  private Integer[] randomIndices;
  private int currentAnimalIndex = 0;

  private final int MAX_CHOICES = 4;

  private Animal[] choices = new Animal[MAX_CHOICES];
  private AnimalLoader animalLoader;

  public Quiz( Context context ) {
    animalLoader = new AnimalLoader(context);

    animals = animalLoader.getAnimalArray();
    Shuffle.shuffle( animals );

    randomIndices = new Integer[animals.length];
    for ( int index = 0; index < randomIndices.length; index++)
      randomIndices[index] = index;
    Shuffle.shuffle(randomIndices);
  }

  public void selectNextAnimal() {
    currentAnimalIndex = (currentAnimalIndex + 1) % animals.length;
    Shuffle.shuffle( randomIndices );
  }

  public Animal[] getChoices() {
    choices[0] = animals[currentAnimalIndex];
    int randomIndex = 0;

    for ( int choiceIndex = 1; choiceIndex < MAX_CHOICES; choiceIndex++ ) {
      if ( randomIndices[randomIndex] == currentAnimalIndex )
        randomIndex++;
      choices[choiceIndex] = animals[randomIndices[randomIndex++]];
    }

    Shuffle.shuffle( choices );

    return choices;
  }

  public Animal getAnswer() {
    return animals[currentAnimalIndex];
  }
  
  public Animal[] getAnimalArray() {
    return animals;
  }
}