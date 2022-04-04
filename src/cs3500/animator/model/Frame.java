package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cs3500.animator.model.interfaces.Drawable;

/**
 * Represents a single frame in an animation. Simple implementation of IFrame.
 * This is used by the animation model to store the state of the animation at a single moment in
 * time and is used by our VisualView class to render our animation model.
 */
public class Frame implements IFrame {

  private final List<Drawable> objects;

  /**
   * Constructor of Frame taking a Collection of Drawable as an argument.
   * Initializes field objects as a list of copies of the objects in the given collection.
   *
   * @param objects the collection of animated objects
   */
  public Frame(Collection<Drawable> objects) {
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
    List<Drawable> copy = new ArrayList<>();

    for (Drawable d : objects) {
      copy.add(d.getCopy());
    }

    return copy;
  }
}
