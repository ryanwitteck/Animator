package cs3500.animator.model.attributes;

/**
 * This class represents a color.
 * Colors are represented by traditional rgb values.
 */
public class Color {

  private int r;
  private int g;
  private int b;

  /**
   * Constructor for color class.
   * Initializes rgb values to the given integer values.
   *
   * @param r the red value
   * @param g the green value
   * @param b the blue value
   */
  public Color(int r, int g, int b) {
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
  public boolean inBounds(int x) {
    return x >= 0 && x <= 255;
  }

  /**
   * Sets red value.
   *
   * @param r the desired red value.
   */
  public void setR(int r) {
    if (!inBounds(r)) {
      throw new IllegalArgumentException("Value must be in range [0-255].");
    }
    this.r = r;
  }

  /**
   * Sets green value.
   *
   * @param g the desired green value.
   */
  public void setG(int g) {
    if (!inBounds(g)) {
      throw new IllegalArgumentException("Value must be in range [0-255].");
    }
    this.g = g;
  }

  /**
   * Sets blue value.
   *
   * @param b the desired blue value.
   */
  public void setB(int b) {
    if (!inBounds(b)) {
      throw new IllegalArgumentException("Value must be in range [0-255].");
    }
    this.b = b;
  }

  /**
   * Sets new Color values.
   *
   * @param r the desired red value.
   * @param g the desired green value.
   * @param b the desired blue value.
   */
  public void setColor(int r, int g, int b) {
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

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other instanceof Color) {
      Color c = (Color) other;
      return this.r == c.r && this.g == c.g && this.b == c.b;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return r + g + b;
  }
}