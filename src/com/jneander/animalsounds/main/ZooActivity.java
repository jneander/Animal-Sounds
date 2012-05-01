package com.jneander.animalsounds.main;

import java.io.IOException;

import com.jneander.animalsounds.R;
import com.jneander.animalsounds.util.DBAdapter;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ZooActivity extends Activity {
	private String[][] animals;
	private int currentAnimal = 0;

	private TextView animalName;
	private TextView animalFacts;
	private ImageView animalView;

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.zoo );

		animalName = (TextView) this.findViewById( R.id.zoo_animal_name );
		animalFacts = (TextView) this.findViewById( R.id.zoo_animal_facts );
		animalView = (ImageView) findViewById( R.id.zoo_animal_image );

		loadDatabaseIntoArray();

		updateAnimalFacts();
		updateAnimalImage();
		updateAnimalSound();
	}

	private void updateAnimalFacts() {
		animalName.setText( animals[currentAnimal][0] );
		animalFacts.setText( animals[currentAnimal][1] );
	}

	private void updateAnimalImage() {
		try {
			animalView.setImageBitmap( BitmapFactory.decodeStream(
					this.getAssets().open(
							"animals/" + animals[currentAnimal][2] ) ) );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
	
	private void updateAnimalSound() {
		
	}

	public void loadNext() {
		currentAnimal = (currentAnimal + 1) % animals.length;
	}

	public void loadLast() {
		currentAnimal = (currentAnimal + animals.length - 1) % animals.length;
	}

	public void loadDatabaseIntoArray() {
		DBAdapter dbAdapter = new DBAdapter( this );
		dbAdapter.open();

		Cursor cursor = dbAdapter.getAllRecords();

		animals = new String[cursor.getCount()][4];
		int index = 0;

		cursor.moveToFirst();
		if ( cursor != null )
			while ( cursor != null && !cursor.isLast() ) {
				animals[index][0] = cursor.getString( 1 );
				animals[index][1] = cursor.getString( 2 );
				animals[index][2] = cursor.getString( 3 );
				animals[index][3] = cursor.getString( 4 );

				cursor.moveToNext();
				index++;
			}

		dbAdapter.close();
	}
}