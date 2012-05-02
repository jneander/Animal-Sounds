package com.jneander.animalsounds.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAccessor {
  private SQLiteDatabase database;
  private DatabaseHelper dbHelper;
  private Context context;

  public DatabaseAccessor( Context context ) {
    this.context = context;
    dbHelper = new DatabaseHelper( context );
  }
}