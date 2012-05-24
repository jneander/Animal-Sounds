package com.jneander.animalsounds.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.jneander.animalsounds.R;

public class MainActivity extends Activity implements OnClickListener {
  @Override
  public void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.main );
    
    this.findViewById( R.id.image_button_to_zoo ).setOnClickListener( this );
    this.findViewById( R.id.image_button_to_quiz ).setOnClickListener( this );
  }

  public void goToZoo() {
    Intent zooIntent = new Intent( this, ZooActivity.class );
    this.startActivity( zooIntent );
  }

  public void goToQuiz() {
    Intent quizIntent = new Intent( this, QuizActivity.class );
    this.startActivity( quizIntent );
  }

  @Override
  public void onClick( View view ) {
    switch ( view.getId() ) {
    case R.id.image_button_to_zoo:
      goToZoo();
      break;
    case R.id.image_button_to_quiz:
      goToQuiz();
      break;
    }
  }
}