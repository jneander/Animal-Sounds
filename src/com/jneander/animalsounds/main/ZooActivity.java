package com.jneander.animalsounds.main;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
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
import com.jneander.animalsounds.util.AnimalLoader;

public class ZooActivity extends Activity implements OnClickListener {
  private Animal[] animals;
  private int currentAnimal = 0;

  private MediaPlayer player = new MediaPlayer();
  private AssetFileDescriptor assetFile;

  private TextView animalName;
  private TextView animalFacts;
  private ImageView animalView;

  private Button lastButton;
  private Button nextButton;

  private AnimalLoader animalLoader;

  @Override
  public void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.zoo );

    animalLoader = new AnimalLoader( this );

    animalName = (TextView) this.findViewById( R.id.zoo_animal_name );
    animalFacts = (TextView) this.findViewById( R.id.zoo_animal_facts );
    animalView = (ImageView) this.findViewById( R.id.zoo_animal_image );
    animalView.setOnClickListener( this );

    lastButton = (Button) this.findViewById( R.id.zoo_last_button );
    nextButton = (Button) this.findViewById( R.id.zoo_next_button );
    lastButton.setOnClickListener( this );
    nextButton.setOnClickListener( this );

    loadAnimalsIntoArray();

    updateAnimalFacts();
    updateAnimalImage();
    updateAnimalSound();
  }

  @Override
  public void onClick( View v ) {
    if ( v.getId() == lastButton.getId() )
      loadLast();
    else if ( v.getId() == nextButton.getId() )
      loadNext();
    else if ( v.getId() == animalView.getId() )
      player.start();
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

  private void updateAnimalSound() {
    try {
      player.reset();
      assetFile = getAssets().openFd( "sounds/" + animals[currentAnimal].getSoundfile() );
      player.setDataSource( assetFile.getFileDescriptor(), assetFile.getStartOffset(), assetFile.getLength() );
      assetFile.close();
      player.prepare();
    } catch ( IOException e ) {
      e.printStackTrace();
    }
  }

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

  public void loadAnimalsIntoArray() {
    animals = animalLoader.getAnimalArray();
  }
}