package cs3500.animator.model.shapes;

import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.interfaces.Drawable;

/**
 * This class represents a rectangle.
 * <p>
 * Contains fields:
 * - All fields in BasicShape by inheritance.
 * - width   the width of this rectangle.
 * - height  the height of this rectangle.
 * Implemented Methods:
 * - All methods in BasicShape by inheritance.
 * - getCopy from Drawable.
 * - All Scalable methods.
 * - Getters for width and height.
 * - Equals and HashCode.
 */
public class Rectangle extends BasicShape {
  private double width;
  private double height;

  /**
   * Sole constructor for Rectangle.
   * Initializes the name, position, dimensions, and color of this shape.
   *
   * @param name   the name of this shape
   * @param x      the initial x coordinate of this shape
   * @param y      the initial y coordinate of this shape
   * @param width  the initial width of this shape.
   * @param height the initial height of this shape.
   * @param color  the initial color of this shape
   */
  public Rectangle(String name, double x, double y, double width, double height, Color color) {
    super(name, x, y, color);
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Cannot have a size less than 0.");
    }
    this.width = width;
    this.height = height;
  }

  @Override
  public Drawable getCopy() {
    Color copy = new Color(color);
    return new Rectangle(name, posn.getX(), posn.getY(), width, height, copy);
  }

  @Override
  public void stretchHorizontal(double scale) {
    scaleError(scale);
    width *= scale;
  }

  @Override
  public void addHorizontal(double dx) {
    width += dx;
  }

  @Override
  public void stretchVertical(double scale) {
    scaleError(scale);
    height *= scale;
  }

  @Override
  public void addVertical(double dy) {
    height += dy;
  }

  /**
   * Get the width of this rectangle.
   *
   * @return width the width of this rectangle
   */
  public double getWidth() {
    return width;
  }

  /**
   * Get the height of this rectangle.
   *
   * @return height the height of this rectangle
   */
  public double getHeight() {
    return height;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof Rectangle) {
      Rectangle r = (Rectangle) obj;
      return this.name.equals(r.name)
              && this.posn.equals(r.posn)
              && this.color.equals(r.color)
              && this.width - r.width < 0.001
              && this.height - r.height < 0.001;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return name.hashCode() + posn.hashCode() + color.hashCode() + Double.hashCode(width + height);
  }
}
