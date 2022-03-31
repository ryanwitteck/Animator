package model.interfaces;

import model.attributes.Color;

/**
 * Our interface for any object that can be drawn onto the canvas.
 *
 * Every Drawable should...
 *  - Have a unique name/ID for easy recognition.
 *  - Have a color.
 *  - Be able to create a copy of itself.
 *  - Be able to return an image of itself or draw itself onto a canvas <--- TODO
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
  void setColor(int r, int g, int b);

  /**
   * Changes the color of this object.
   *
   * @param c the color
   */
  void setColor(Color c);

  // TODO -- Add draw method. Do after behavior is decided.
}
