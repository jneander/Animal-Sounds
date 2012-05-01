package com.jneander.animalsounds.test;

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
}