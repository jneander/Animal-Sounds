package com.jneander.animalsounds.test;

import android.test.ActivityInstrumentationTestCase2;

import com.jneander.animalsounds.main.QuizActivity;
import com.jneander.animalsounds.obj.Animal;
import com.jneander.animalsounds.obj.Quiz;

public class QuizTest extends ActivityInstrumentationTestCase2< QuizActivity > {
  private QuizActivity activity;
  private Quiz quiz;

  public QuizTest() {
    super( QuizActivity.class.getPackage().getName(), QuizActivity.class );
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    activity = getActivity();

    quiz = new Quiz( activity );
  }

  public void testGetUniqueChoices() {
    Animal[] quizChoices = quiz.getChoices();
    assertNotNull( quizChoices );

    assertFalse( hasDuplicates( quizChoices ) );
  }

  public < T > boolean hasDuplicates( T[] array ) {
    boolean result = false;

    for ( int firstIndex = 0; firstIndex < array.length - 1; firstIndex++ )
      for ( int secondIndex = firstIndex + 1; secondIndex < array.length; secondIndex++ )
        if ( array[firstIndex] == array[secondIndex] )
          result = true;

    return result;
  }
}