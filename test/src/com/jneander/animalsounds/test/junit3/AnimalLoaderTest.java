package com.jneander.animalsounds.test.junit3;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.jneander.animalsounds.R;
import com.jneander.animalsounds.main.MainActivity;

public class AnimalLoaderTest extends ActivityInstrumentationTestCase2< MainActivity > {
  private MainActivity activity;

  public AnimalLoaderTest() {
    super( MainActivity.class.getPackage().getName(), MainActivity.class );
  }

  protected void setUp() throws Exception {
    super.setUp();
    activity = getActivity();
  }

  public void testCanAccessArrayOfAnimalNames() {
    String[] animal_names = activity.getResources().getStringArray( R.array.animal_names );
    assertTrue( animal_names.length > 0 );
  }

  public void testCanAccessArrayOfAnimalFacts() {
    String[] animal_facts = activity.getResources().getStringArray( R.array.animal_facts );
    assertTrue( animal_facts.length > 0 );
  }

  public void testCanAccessArrayOfAnimalImages() {
    String[] animal_images = activity.getResources().getStringArray( R.array.animal_images );
    assertTrue( animal_images.length > 0 );
  }

  public void testCanAccessArrayOfAnimalSounds() {
    String[] animal_sounds = activity.getResources().getStringArray( R.array.animal_sounds );
    assertTrue( animal_sounds.length > 0 );
  }
}