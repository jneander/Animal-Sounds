package com.jneander.animalsounds.main;

import java.io.IOException;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jneander.animalsounds.R;
import com.jneander.animalsounds.obj.Animal;
import com.jneander.animalsounds.util.DatabaseAccessor;

public class ZooActivity extends Activity implements OnClickListener {
  private Animal[] animals;
  private int currentAnimal = 0;
  
  private MediaPlayer player;

  private TextView animalName;
  private TextView animalFacts;
  private ImageView animalView;

  private Button prevButton;
  private Button nextButton;

  private DatabaseAccessor dbAccessor;

  @Override
  public void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.zoo );

    dbAccessor = new DatabaseAccessor( this );
    
    animalName = (TextView) this.findViewById( R.id.zoo_animal_name );
    animalFacts = (TextView) this.findViewById( R.id.zoo_animal_facts );
    animalView = (ImageView) findViewById( R.id.zoo_animal_image );

    prevButton = (Button) this.findViewById( R.id.zoo_last_button );
    nextButton = (Button) this.findViewById( R.id.zoo_next_button );
    prevButton.setOnClickListener( this );
    nextButton.setOnClickListener( this );

    loadDatabaseIntoArray();

    updateAnimalFacts();
    updateAnimalImage();
    updateAnimalSound();
  }

  @Override
  public void onClick( View v ) {
    if ( v.getId() == R.id.zoo_last_button )
      loadLast();
    else if ( v.getId() == R.id.zoo_next_button )
      loadNext();
  }

  private void updateAnimalFacts() {
    animalName.setText( animals[currentAnimal].getName() );
    animalFacts.setText( animals[currentAnimal].getFacts() );
  }

  private void updateAnimalImage() {
    try {
      animalView.setImageBitmap( BitmapFactory.decodeStream(
          this.getAssets().open(
              "images/" + animals[currentAnimal].getImagefile() ) ) );
    } catch ( IOException e ) {
      e.printStackTrace();
    }
  }

  private void updateAnimalSound() {}

  public void loadNext() {
    currentAnimal = (currentAnimal + 1) % animals.length;
    updateAnimalFacts();
    updateAnimalImage();
    updateAnimalSound();
  }

  public void loadLast() {
    currentAnimal = (currentAnimal + animals.length - 1) % animals.length;
    updateAnimalFacts();
    updateAnimalImage();
    updateAnimalSound();
  }

  public void loadDatabaseIntoArray() {
    animals = dbAccessor.getAnimalsArray();
  }
}