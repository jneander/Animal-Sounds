package com.jneander.animalsounds.test;

import android.test.ActivityInstrumentationTestCase2;

import com.jneander.animalsounds.main.MainActivity;

public class DatabaseAccessorTest extends ActivityInstrumentationTestCase2< MainActivity > {
  private DatabaseAccessor dbAccessor;
  private final String[] dbAnimalsNames = new String[] { "Giraffe", "Squirrel", "Horse" };

  public DatabaseAccessorTest() {
    super( MainActivity.class.getPackage().getName(), MainActivity.class );
  }

  protected void setUp() throws Exception {
    super.setUp();

  }
}