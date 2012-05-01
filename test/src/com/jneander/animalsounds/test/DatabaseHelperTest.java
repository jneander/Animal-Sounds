package com.jneander.animalsounds.test;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.test.ActivityInstrumentationTestCase2;

import com.jneander.animalsounds.main.MainActivity;
import com.jneander.animalsounds.util.DatabaseHelper;

public class DatabaseHelperTest extends ActivityInstrumentationTestCase2< MainActivity > {
  private MainActivity activity;
  private DatabaseHelper dbHelper;

  private SQLiteDatabase database;
  private String DB_PATH;
  private String DB_NAME = "animalData.db";

  public DatabaseHelperTest() {
    super( MainActivity.class.getPackage().getName(), MainActivity.class );
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    activity = getActivity();

    dbHelper = new DatabaseHelper( activity );
    DB_PATH = "/data/data/" + activity.getPackageName() + "/databases/";
  }

  public void testDatabaseFileExists() throws IOException {
    AssetManager assets = activity.getAssets();
    assertNotNull( assets );

    String[] assetList = assets.list( "" );
    assertNotNull( assetList );
    assertTrue( assetList.length > 0 );

    InputStream input = assets.open( DB_NAME );
    assertNotNull( input );

    if ( input != null )
      input.close();
  }

  public void testDatabaseWasCreated() {
    assertFalse( databaseExists() );
    database = dbHelper.getReadableDatabase();
    dbHelper.close();
    assertTrue( databaseExists() );
  }

  private boolean databaseExists() {
    SQLiteDatabase checkDB = null;

    try {
      checkDB = SQLiteDatabase.openDatabase( DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READONLY );
      checkDB.close();
    } catch ( Exception e ) {}

    return checkDB != null;
  }
}