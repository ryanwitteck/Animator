package model;

import java.util.List;

import model.ObjectInterfaces.Drawable;

/**
 * Represents a single frame in an animation.
 */
public interface IFrame {
  /**
   * Get the list of animated objects.
   *
   * @return the list of objects being animated
   */
  List<Drawable> listObjects();
}
