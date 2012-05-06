package com.jneander.animalsounds.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.jneander.animalsounds.main.MainActivity;
import com.jneander.animalsounds.obj.Animal;
import com.jneander.animalsounds.util.DatabaseAccessor;

public class DatabaseAccessorTest extends ActivityInstrumentationTestCase2< MainActivity > {
  private MainActivity activity;
  private DatabaseAccessor dbAccessor;

  public DatabaseAccessorTest() {
    super( MainActivity.class.getPackage().getName(), MainActivity.class );
  }

  protected void setUp() throws Exception {
    super.setUp();
    activity = getActivity();

    dbAccessor = new DatabaseAccessor( activity );
  }

  public void testAccessorAnimalNames() {
    String[] accessorAnimalsNames = dbAccessor.getAnimalsNames();
    assertTrue( accessorAnimalsNames.length > 0 );
  }

  public void testAccessorGetAnimals() {
    Animal[] accessorAnimals = dbAccessor.getAnimalsArray();
    assertNotNull( accessorAnimals );

    for ( Animal currentAnimal : accessorAnimals ) {
      logAnimal( currentAnimal );
      assertNotNull( currentAnimal.getId() );
      assertNotNull( currentAnimal.getName() );

      assertNotNull( currentAnimal.getFacts() );
      assertNotNull( currentAnimal.getImagefile() );
      assertNotNull( currentAnimal.getSoundfile() );
    }
  }

  public void logAnimal( Animal animal ) {
    Log.w( activity.getPackageName(),
        String.format( "Animal -> id: %d, name: %s, files: %s|%s",
            animal.getId(), animal.getName(), animal.getImagefile(), animal.getSoundfile()
            ) );
  }
}