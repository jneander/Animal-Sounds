package com.jneander.animalsounds.test.junit3;

import java.io.IOException;

import android.test.ActivityInstrumentationTestCase2;

import com.jneander.animalsounds.R;
import com.jneander.animalsounds.main.MainActivity;
import com.jneander.animalsounds.obj.Animal;
import com.jneander.animalsounds.util.AnimalLoader;

public class AnimalLoaderTest extends ActivityInstrumentationTestCase2< MainActivity > {
  private final String TAG = "AnimalLoaderTest";
  private MainActivity activity;

  public AnimalLoaderTest() {
    super( MainActivity.class.getPackage().getName(), MainActivity.class );
  }

  protected void setUp() throws Exception {
    super.setUp();
    activity = getActivity();
  }

  public void testCanAccessArrayOfAnimalNames() {
    String[] animalNames = activity.getResources().getStringArray( R.array.animal_names );
    assertTrue( animalNames.length > 0 );
  }

  public void testCanAccessArrayOfAnimalFacts() {
    String[] animalFacts = activity.getResources().getStringArray( R.array.animal_facts );
    assertTrue( animalFacts.length > 0 );
  }

  public void testCanAccessArrayOfAnimalImages() {
    String[] animalImages = activity.getResources().getStringArray( R.array.animal_images );
    assertTrue( animalImages.length > 0 );
  }

  public void testCanAccessArrayOfAnimalSounds() {
    String[] animalSounds = activity.getResources().getStringArray( R.array.animal_sounds );
    assertTrue( animalSounds.length > 0 );
  }

  public void testImagesArrayHasValidAssetNames() throws IOException {
    String[] animalImages = activity.getResources().getStringArray( R.array.animal_images );
    String[] imageAssets = activity.getAssets().list( "images" );

    checkListedAssetsExist( animalImages, imageAssets );
  }

  public void testSoundsArrayHasValidAssetNames() throws IOException {
    String[] animalSounds = activity.getResources().getStringArray( R.array.animal_sounds );
    String[] soundAssets = activity.getAssets().list( "sounds" );

    checkListedAssetsExist( animalSounds, soundAssets );
  }

  public void testAnimalArrayHasValidData() {
    AnimalLoader animalLoader = new AnimalLoader( activity );
    Animal[] animals = animalLoader.getAnimalArray();

    assertTrue( animals.length > 0 );

    for ( Animal animal : animals ) {
      assertNotNull( animal );
      assertNotNull( animal.getName() );
      assertNotNull( animal.getFacts() );
      assertNotNull( animal.getImagefile() );
      assertNotNull( animal.getSoundfile() );
    }
  }

  private void checkListedAssetsExist( String[] requiredArray, String[] assetArray ) {
    for ( int reqIndex = 0; reqIndex < requiredArray.length; reqIndex++ ) {
      boolean matchFound = false;

      for ( int assetIndex = 0; assetIndex < assetArray.length; assetIndex++ )
        matchFound |= assetArray[assetIndex].equals( requiredArray[reqIndex] );

      assertTrue( matchFound );
    }
  }
}