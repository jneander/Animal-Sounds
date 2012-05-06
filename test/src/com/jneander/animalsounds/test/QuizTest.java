package com.jneander.animalsounds.test;

import android.test.ActivityInstrumentationTestCase2;

import com.jneander.animalsounds.main.QuizActivity;

public class QuizTest extends ActivityInstrumentationTestCase2< QuizActivity > {

  private QuizActivity activity;

  public QuizTest() {
    super( QuizActivity.class.getPackage().getName(), QuizActivity.class );
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    activity = getActivity();
  }
}