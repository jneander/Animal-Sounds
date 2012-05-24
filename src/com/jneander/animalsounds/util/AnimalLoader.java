package com.jneander.animalsounds.util;

import android.content.Context;

import com.jneander.animalsounds.R;
import com.jneander.animalsounds.obj.Animal;

public class AnimalLoader {
  private Animal[] animals;

  public AnimalLoader( Context context ) {
    loadAnimalsFromResources( context );
  }

  public Animal[] getAnimalArray() {
    return this.animals;
  }

  private void loadAnimalsFromResources( Context context ) {
    String[] animalNames = context.getResources().getStringArray( R.array.animal_names );
    String[] animalFacts = context.getResources().getStringArray( R.array.animal_facts );
    String[] animalImages = context.getResources().getStringArray( R.array.animal_images );
    String[] animalSounds = context.getResources().getStringArray( R.array.animal_sounds );

    animals = new Animal[animalNames.length];

    for ( int animalIndex = 0; animalIndex < animalNames.length; animalIndex++ ) {
      Animal animal = new Animal();

      animal.setName( animalNames[animalIndex] );
      animal.setFacts( animalFacts[animalIndex] );
      animal.setImagefile( animalImages[animalIndex] );
      animal.setSoundfile( animalSounds[animalIndex] );

      animals[animalIndex] = animal;
    }
  }
}