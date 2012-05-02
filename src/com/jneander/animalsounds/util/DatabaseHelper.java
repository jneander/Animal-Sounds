package com.jneander.animalsounds.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
  private static final int DB_VERSION = 1;
  private static final String DB_NAME = "animalData.db";
  private String DB_PATH;
  
  private Context context;

  public DatabaseHelper( Context context ) {
    super( context, DB_NAME, null, DB_VERSION );
    
    this.context = context;
    DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
    
    copyDatabaseFromAssets();
  }

  @Override
  public void onCreate( SQLiteDatabase db ) {}

  @Override
  public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {}
  
  private void copyDatabaseFromAssets() {
    try {
      InputStream input = context.getAssets().open( DB_NAME );
      OutputStream output = new FileOutputStream( DB_PATH + DB_NAME );

      byte[] buffer = new byte[1024];
      int length;

      while ( (length = input.read( buffer )) > 0 )
        output.write( buffer, 0, length );

      output.flush();
      output.close();
      input.close();

      Log.w( context.getPackageName(), "success: copyDatabaseFromAssets" );
    } catch ( IOException e ) {
      Log.w( "onCreate", "There was an error copying the database." );
      e.printStackTrace();
    }
  }
}