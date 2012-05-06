package com.jneander.animalsounds.obj;

import android.content.Context;

import com.jneander.animalsounds.util.DatabaseAccessor;

public class Quiz {
  Animal[] animals;
  DatabaseAccessor dbAccessor;
  Context context;

  public Quiz( Context context ) {
    this.context = context;
    this.dbAccessor = new DatabaseAccessor( context );
    
    animals = dbAccessor.getAnimalsArray();
  }
}