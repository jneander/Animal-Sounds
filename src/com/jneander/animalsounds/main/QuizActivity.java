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
import com.jneander.animalsounds.obj.Quiz;

public class QuizActivity extends Activity implements OnClickListener {
  private Quiz quiz;
  private Animal[] animalChoices;
  private Animal animalAnswer;

  private MediaPlayer player = new MediaPlayer();
  private AssetFileDescriptor assetFile;

  private TextView animalFacts;
  private ImageView animalView;

  private Button choice_1;
  private Button choice_2;
  private Button choice_3;
  private Button choice_4;

  private Button nextButton;

  @Override
  public void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.quiz );

    quiz = new Quiz( this );
    loadQuizData();

    animalFacts = (TextView) this.findViewById( R.id.quiz_animal_facts );
    animalView = (ImageView) this.findViewById( R.id.quiz_animal_image );
    animalView.setOnClickListener( this );

    choice_1 = (Button) this.findViewById( R.id.quiz_choice_1 );
    choice_2 = (Button) this.findViewById( R.id.quiz_choice_2 );
    choice_3 = (Button) this.findViewById( R.id.quiz_choice_3 );
    choice_4 = (Button) this.findViewById( R.id.quiz_choice_4 );
    choice_1.setOnClickListener( this );
    choice_2.setOnClickListener( this );
    choice_3.setOnClickListener( this );
    choice_4.setOnClickListener( this );

    nextButton = (Button) this.findViewById( R.id.quiz_next_button );
    nextButton.setOnClickListener( this );

    updateAnimalFacts();
    updateAnimalImage();
    updateAnimalSound();
    updateButtonChoices();
  }

  @Override
  public void onClick( View view ) {
    if ( view == animalView ) {
      player.start();
    } else if ( view == choice_1 ) {

    } else if ( view == choice_2 ) {

    } else if ( view == choice_3 ) {

    } else if ( view == choice_4 ) {

    } else if ( view == nextButton ) {
      quiz.selectNextAnimal();
      loadQuizData();
      
      updateAnimalFacts();
      updateAnimalImage();
      updateAnimalSound();
      updateButtonChoices();
    }
  }

  private void loadQuizData() {
    animalChoices = quiz.getChoices();
    animalAnswer = quiz.getAnswer();
  }

  private void updateAnimalFacts() {
    animalFacts.setText( animalAnswer.getFacts() );
  }

  private void updateAnimalImage() {
    try {
      animalView.setImageBitmap( BitmapFactory.decodeStream(
          this.getAssets().open(
              "images/" + animalAnswer.getImagefile() ) ) );
    } catch ( IOException e ) {
      e.printStackTrace();
    }
  }

  private void updateAnimalSound() {
    try {
      player.reset();
      assetFile = getAssets().openFd( "sounds/" + animalAnswer.getSoundfile() );
      player.setDataSource( assetFile.getFileDescriptor(), assetFile.getStartOffset(), assetFile.getLength() );
      assetFile.close();
      player.prepare();
    } catch ( IOException e ) {
      e.printStackTrace();
    }
  }

  private void updateButtonChoices() {
    choice_1.setText( animalChoices[0].getName() );
    choice_2.setText( animalChoices[1].getName() );
    choice_3.setText( animalChoices[2].getName() );
    choice_4.setText( animalChoices[3].getName() );
  }
}