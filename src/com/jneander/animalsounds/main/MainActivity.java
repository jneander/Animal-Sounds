package com.jneander.animalsounds.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jneander.animalsounds.R;

public class MainActivity extends Activity {
  @Override
  public void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.main );
  }

  public void goToZoo( View v ) {
    Intent zooIntent = new Intent( this, ZooActivity.class );
    this.startActivity( zooIntent );
  }
  
  public void goToQuiz( View v ) {
    Intent quizIntent = new Intent( this, QuizActivity.class );
    this.startActivity( quizIntent );
  }
}