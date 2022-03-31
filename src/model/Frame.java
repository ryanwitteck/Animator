package model;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.Drawable;

/**
 * Represents a single frame in an animation. Simple implementation of IFrame.
 */
public class Frame implements IFrame {

  private final List<Drawable> objects;

  /**
   * Constructor of Frame.
   *
   * @param objects the animated objects
   */
  public Frame(List<Drawable> objects) {
    if (objects == null) {
      throw new IllegalArgumentException("Cannot have null arguments.");
    }
    this.objects = new ArrayList<>();

    for (Drawable d : objects) {
      this.objects.add(d.getCopy());
    }
  }

  @Override
  public List<Drawable> listObjects() {
    return new ArrayList<>(objects);
  }
}
