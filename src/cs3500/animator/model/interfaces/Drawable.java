package cs3500.animator.model.interfaces;

import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.attributes.Posn;

/**
 * Our interface for any object that can be drawn onto the canvas.
 * Every Drawable should...
 * - Have a unique name/ID for recognition.
 * - Have a position on the canvas.
 * - Have a color.
 * - Be able to create a copy of itself.
 */
public interface Drawable {

  /**
   * Get a copy of this Drawable.
   *
   * @return the copy of this Drawable.
   */
  Drawable getCopy();

  /**
   * Get the name of this object.
   *
   * @return the name of this object.
   */
  String getName();

  /**
   * Get the current position of this object.
   *
   * @return the position of this object.
   */
  Posn getPos();

  /**
   * Get the color of this object.
   *
   * @return the color of this object.
   */
  Color getColor();

  /**
   * Changes the color of this object.
   *
   * @param r the red value
   * @param g the green value
   * @param b the blue value
   */
  void setColor(double r, double g, double b);

  /**
   * Changes the color of this object.
   *
   * @param c the color
   */
  void setColor(Color c);
}
