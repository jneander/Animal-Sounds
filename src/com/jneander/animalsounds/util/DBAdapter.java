package com.jneander.animalsounds.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	private static final String KEY_ID = "_id";
	private static final String KEY_NAME = "name";
	private static final String KEY_FACTS = "facts";
	private static final String KEY_IMAGE = "imagefile";
	private static final String KEY_SOUND = "soundfile";

	private static final String DB_NAME = "testDatabase";
	private static final String DB_TABLE = "animals";
	private static final int DB_VERSION = 1;

	@SuppressWarnings( "unused" )
	private static final String TAG = "DBAdapter";

	@SuppressWarnings( "unused" )
	private static final String DB_CREATE =
			"CREATE TABLE IF NOT EXISTS " + DB_TABLE + "("
					+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_NAME + " TEXT, "
					+ KEY_FACTS + " TEXT, "
					+ KEY_IMAGE + " TEXT, "
					+ KEY_SOUND + " TEXT )";
	
	private final String DB_PATH;

	@SuppressWarnings( "unused" )
	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase database;

	public DBAdapter( Context context ) {
		this.context = context;
		DBHelper = new DatabaseHelper( context );
		
		DB_PATH = "/data/data/" + context.getPackageName() + "/";
	}

	private class DatabaseHelper extends SQLiteOpenHelper {
		private Context context;
		
		public DatabaseHelper( Context context ) {
			super( context, DB_NAME, null, DB_VERSION );
			this.context = context;
		}

		@Override
		public void onCreate( SQLiteDatabase db ) {
		}

		@Override
		public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
		}
		
		private boolean databaseExists() {
			SQLiteDatabase db = null;
			
			try {
				db = SQLiteDatabase.openDatabase( DB_NAME, null, SQLiteDatabase.OPEN_READONLY );
			} catch ( SQLiteException e ) {
				
			}
			
			if ( db != null )
				db.close();
			
			return (db != null) ? true : false;
		}
	
		public void createDatabase() throws IOException {
			if ( !databaseExists()) {
				this.getReadableDatabase();
				
			}
			
		}
		
		public void copyStreamToDatabase() throws IOException {
			InputStream input = context.getAssets().open( DB_NAME );
			OutputStream output = new FileOutputStream( DB_PATH + DB_NAME );
			
			byte[] buffer = new byte[1024];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write( buffer, 0, length );
			}
			
			output.flush();
			output.close();
			input.close();
		}
		
		public SQLiteDatabase openDatabase() throws SQLException {
			return SQLiteDatabase.openDatabase( DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READONLY );
		}
		
		@Override
		public synchronized void close() {
		}
	}

	public DBAdapter open() throws SQLException {
		database = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}
	
	public Cursor getAllRecords() {
		return database.query( DB_TABLE, new String[] { KEY_ID, KEY_NAME, KEY_FACTS, KEY_IMAGE, KEY_SOUND },
				null, null, null, null, null );
	}
}