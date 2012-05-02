package com.jneander.animalsounds.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    Cursor cursor = database.rawQuery( "SELECT * FROM animals", null );
    
    int entryCount = cursor.getCount();
    String[] outputArray = new String[entryCount];
    
    cursor.moveToFirst();
    for ( int entryIndex = 0; entryIndex < entryCount; entryIndex++) {
      outputArray[entryIndex] = cursor.getString( 1 );
      cursor.moveToNext();
    }
    
    cursor.close();
    dbHelper.close();
    
    return outputArray;
  }
}