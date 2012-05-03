package com.jneander.animalsounds.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jneander.animalsounds.obj.Animal;

public class DatabaseAccessor {
  private SQLiteDatabase database;
  private DatabaseHelper dbHelper;
  private Context context;

  public DatabaseAccessor( Context context ) {
    this.context = context;
    dbHelper = new DatabaseHelper( context );
  }

  public String[] getAnimalsNames() {
    database = dbHelper.getReadableDatabase();
    Cursor cursor = getAnimalsCursor();

    int entryCount = cursor.getCount();
    String[] outputArray = new String[entryCount];

    cursor.moveToFirst();
    for ( int entryIndex = 0; entryIndex < entryCount; entryIndex++ ) {
      outputArray[entryIndex] = cursor.getString( 1 );
      cursor.moveToNext();
    }

    cursor.close();
    dbHelper.close();

    return outputArray;
  }

  public Animal[] getAnimalsArray() {
    database = dbHelper.getReadableDatabase();
    Cursor cursor = getAnimalsCursor();

    int entryCount = cursor.getCount();
    Animal[] outputArray = new Animal[entryCount];

    cursor.moveToFirst();
    for ( int entryIndex = 0; entryIndex < entryCount; entryIndex++ ) {
      Animal currentAnimal = new Animal();

      currentAnimal.setId( cursor.getInt( 0 ) );
      currentAnimal.setName( cursor.getString( 1 ) );
      currentAnimal.setFacts( cursor.getString( 2 ) );
      currentAnimal.setImagefile( cursor.getString( 3 ) );
      currentAnimal.setSoundfile( cursor.getString( 4 ) );

      outputArray[entryIndex] = currentAnimal;
      cursor.moveToNext();
    }

    cursor.close();
    dbHelper.close();

    return outputArray;
  }

  private Cursor getAnimalsCursor() {
    return database.rawQuery( "SELECT * FROM animals", null );
  }
}