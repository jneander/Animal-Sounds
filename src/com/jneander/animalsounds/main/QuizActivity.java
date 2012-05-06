package com.jneander.animalsounds.main;

import android.app.Activity;
import android.os.Bundle;

import com.jneander.animalsounds.R;

public class QuizActivity extends Activity {
  @Override
  public void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.quiz );
  }
}