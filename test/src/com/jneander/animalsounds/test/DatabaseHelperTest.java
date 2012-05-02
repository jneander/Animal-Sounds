package com.jneander.animalsounds.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.res.AssetManager;
import android.database.Cursor;
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

  private final String[] dbTableNames = new String[] { "android_metadata", "animals" };
  private final String[] dbAnimalsColumns = new String[] { "_id", "name", "facts", "imagefile", "soundfile" };
  private final String[] dbAnimalsNames = new String[] { "Giraffe", "Squirrel", "Horse" };
  private final int dbAnimalsNameColumnIndex = 1;

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
    if ( databaseExists() )
      removeExistingDatabase();
    assertFalse( databaseExists() );

    loadDatabase();
    assertTrue( databaseExists() );
  }

  public void testDatabaseWasRemoved() {
    loadDatabase();
    assertTrue( databaseExists() );

    removeExistingDatabase();
    assertFalse( databaseExists() );
  }

  public void testDatabaseHasTables() {
    loadDatabase();
    assertTrue( databaseExists() );

    openDatabase();

    Cursor cursor = getTableCursor();
    assertNotNull( cursor );

    List< String > tableNames = new ArrayList< String >();

    if ( cursor.moveToFirst() )
      while ( !cursor.isAfterLast() ) {
        tableNames.add( cursor.getString( 0 ) );
        cursor.moveToNext();
      }
    cursor.close();

    assertTrue( tableNames.size() == dbTableNames.length );
    assertTrue( tableNames.containsAll( Arrays.asList( dbTableNames ) ) );

    closeDatabase();
  }

  public void testDatabaseHasColumns() {
    loadDatabase();
    assertTrue( databaseExists() );

    openDatabase();

    Cursor cursor = getAnimalsCursor();
    assertNotNull( cursor );

    List< String > columnNames = new ArrayList< String >();
    int columnCount = cursor.getColumnCount();

    if ( cursor.moveToFirst() )
      for ( int columnIndex = 0; columnIndex < columnCount; columnIndex++ )
        columnNames.add( cursor.getColumnName( columnIndex ) );

    assertTrue( columnNames.size() == dbAnimalsColumns.length );
    assertTrue( columnNames.containsAll( Arrays.asList( dbAnimalsColumns ) ) );

    closeDatabase();
  }

  public void testDatabaseHasEntries() {
    loadDatabase();
    assertTrue( databaseExists() );

    openDatabase();

    Cursor cursor = getAnimalsCursor();
    assertNotNull( cursor );

    List< String > tableEntries = new ArrayList< String >();

    if ( cursor.moveToFirst() )
      while ( !cursor.isAfterLast() ) {
        tableEntries.add( cursor.getString( dbAnimalsNameColumnIndex ) );
        cursor.moveToNext();
      }
    cursor.close();

    assertTrue( tableEntries.size() == dbAnimalsNames.length );
    assertTrue( tableEntries.containsAll( Arrays.asList( dbAnimalsNames ) ) );

    closeDatabase();
  }

  private void loadDatabase() {
    if ( !databaseExists() ) {
      openDatabase();
      closeDatabase();
    }
  }

  private void openDatabase() {
    database = dbHelper.getReadableDatabase();
  }

  private void closeDatabase() {
    dbHelper.close();
  }

  private void removeExistingDatabase() {
    activity.deleteDatabase( DB_NAME );
  }

  private boolean databaseExists() {
    SQLiteDatabase checkDB = null;

    try {
      checkDB = SQLiteDatabase.openDatabase( DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READONLY );
      checkDB.close();
    } catch ( Exception e ) {}

    return checkDB != null;
  }

  private Cursor getTableCursor() {
    return database.rawQuery( "SELECT name FROM sqlite_master WHERE type='table'", null );
  }

  private Cursor getAnimalsCursor() {
    return database.rawQuery( "SELECT * FROM animals", null );
  }
}