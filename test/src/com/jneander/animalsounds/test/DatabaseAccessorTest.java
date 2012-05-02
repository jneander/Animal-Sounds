package com.jneander.animalsounds.test;

import android.test.ActivityInstrumentationTestCase2;

import com.jneander.animalsounds.main.MainActivity;
import com.jneander.animalsounds.util.DatabaseAccessor;

public class DatabaseAccessorTest extends ActivityInstrumentationTestCase2< MainActivity > {
  private MainActivity activity;
  private DatabaseAccessor dbAccessor;
  private final String[] dbAnimalsNames = new String[] { "Giraffe", "Squirrel", "Horse" };

  public DatabaseAccessorTest() {
    super( MainActivity.class.getPackage().getName(), MainActivity.class );
  }

  protected void setUp() throws Exception {
    super.setUp();
    activity = getActivity();
    
    dbAccessor = new DatabaseAccessor( activity );
  }
  
  public void testAccessorAnimalNames() {
    
  }
}