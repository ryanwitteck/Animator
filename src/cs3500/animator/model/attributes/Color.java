package cs3500.animator.model.attributes;

/**
 * This class represents a color.
 * Colors are represented by traditional rgb values and each color value is stored as a double.
 * This class implements getter methods that allow the user to receive the RGB values of a Color
 * and various setter methods that allow for the alteration of the RGB values in a Color.
 */
public class Color {

  private double r;
  private double g;
  private double b;

  /**
   * Constructor for color class.
   * Initializes rgb values to the given double values.
   *
   * @param r the red value
   * @param g the green value
   * @param b the blue value
   */
  public Color(double r, double g, double b) {
    if (!(inBounds(r) && inBounds(g) && inBounds(b))) {
      throw new IllegalArgumentException("Values must be in range [0-255].");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Another constructor for color class.
   * Initializes rgb values to the rgb values of the given color.
   *
   * @param other the desired color
   */
  public Color(Color other) {
    this.r = other.r;
    this.g = other.g;
    this.b = other.b;
  }

  /**
   * Returns whether or not the given number is a valid color value.
   * A value is valid if it is in the range [0, 255].
   *
   * @param x the number
   * @return if it is in bounds.
   */
  public boolean inBounds(double x) {
    return x >= 0 && x <= 255;
  }

  /**
   * Sets red value.
   *
   * @param r the desired red value.
   * @throws IllegalArgumentException if the given value is outside of the range [0, 255].
   */
  public void setR(double r) {
    if (!inBounds(r)) {
      throw new IllegalArgumentException("Value must be in range [0, 255].");
    }
    this.r = r;
  }

  /**
   * Sets green value.
   *
   * @param g the desired green value.
   * @throws IllegalArgumentException if the given value is outside of the range [0, 255].
   */
  public void setG(double g) {
    if (!inBounds(g)) {
      throw new IllegalArgumentException("Value must be in range [0, 255].");
    }
    this.g = g;
  }

  /**
   * Sets blue value.
   *
   * @param b the desired blue value.
   * @throws IllegalArgumentException if the given value is outside of the range [0, 255].
   */
  public void setB(double b) {
    if (!inBounds(b)) {
      throw new IllegalArgumentException("Value must be in range [0, 255].");
    }
    this.b = b;
  }

  /**
   * Increment RGB values by given values.
   * If the any RGB value goes out of bounds it is set to either 0 or 255
   * depending on if it is too low or high.
   *
   * @param dr the change in red value.
   * @param dg the change in green value.
   * @param db the change in blue value.
   */
  public void changeColor(double dr, double dg, double db) {
    r += dr;
    g += dg;
    b += db;
    // keep red in bounds
    if (r < 0) {
      r = 0;
    } else if (r > 255) {
      r = 255;
    }
    // keep green in bounds
    if (g < 0) {
      g = 0;
    } else if (g > 255) {
      g = 255;
    }
    // keep blue in bounds
    if (b < 0) {
      b = 0;
    } else if (b > 255) {
      b = 255;
    }
  }

  /**
   * Sets new Color values.
   *
   * @param r the desired red value.
   * @param g the desired green value.
   * @param b the desired blue value.
   */
  public void setColor(double r, double g, double b) {
    if (!(inBounds(r) && inBounds(g) && inBounds(b))) {
      throw new IllegalArgumentException("Values must be in range [0-255].");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Sets new Color values.
   *
   * @param c the color to copy.
   */
  public void setColor(Color c) {
    if (c == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }
    this.r = c.r;
    this.g = c.g;
    this.b = c.b;
  }

  /**
   * Returns the red value.
   */
  public double getR() {
    return r;
  }

  /**
   * Returns the green value.
   */
  public double getG() {
    return g;
  }

  /**
   * Returns the blue value.
   */
  public double getB() {
    return b;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other instanceof Color) {
      Color c = (Color) other;
      return Math.abs(this.r - c.r) < 0.001
              && Math.abs(this.g - c.g) < 0.001
              && Math.abs(this.b - c.b) < 0.001;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Double.hashCode(r + g + b);
  }

  @Override
  public String toString() {
    return "(" + Math.round(r * 1000) / 1000.0 + ","
            + Math.round(g * 1000) / 1000.0 + "," + Math.round(b * 1000) / 1000.0 + ")";
  }
}