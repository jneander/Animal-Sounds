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
}