package com.jneander.animalsounds.obj;

public class Animal {
  private int id;
  private String name;
  private String facts;
  private String imagefile;
  private String soundfile;

  public int getId() {
    return id;
  }

  public void setId( int id ) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public String getFacts() {
    return facts;
  }

  public void setFacts( String facts ) {
    this.facts = facts;
  }

  public String getImagefile() {
    return imagefile;
  }

  public void setImagefile( String imagefile ) {
    this.imagefile = imagefile;
  }

  public String getSoundfile() {
    return soundfile;
  }

  public void setSoundfile( String soundfile ) {
    this.soundfile = soundfile;
  }
}