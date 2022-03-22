package model.ObjectInterfaces;

import model.Color;

/**
 * Our interface for any object that can be drawn when animated in the future.
 */
public interface Drawable {
  /**
   * Get the name of this object.
   * @return the name of this object.
   */
  public String getName();

  /**
   * Changes the color of this object.
   *
   * @param r the red value
   * @param g the green value
   * @param b the blue value
   */
  void setColor(int r, int g, int b);

  /**
   * Changes the color of this object.
   *
   * @param c the color
   */
  void setColor(Color c);

  // TODO -- Add draw method. Do after behavior is defined.
}
