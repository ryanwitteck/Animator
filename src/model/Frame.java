package model;

import java.util.ArrayList;
import java.util.List;

import model.ObjectInterfaces.Drawable;

public class Frame implements IFrame {
  private final List<Drawable> objects;

  public Frame(List<Drawable> objects) {
    if (objects == null) {
      throw new IllegalArgumentException("Cannot have null arguments.");
    }
    this.objects = new ArrayList<Drawable>();

    for (Drawable d : objects) {
      this.objects.add(d.getCopy());
    }
  }

  @Override
  public List<Drawable> listObjects() {
    return objects;
  }
}
