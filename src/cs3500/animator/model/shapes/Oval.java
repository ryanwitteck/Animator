package cs3500.animator.model.shapes;

import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.interfaces.Drawable;

/**
 * This class represents an oval.
 * <p>
 * Contains fields:
 * - All fields in BasicShape by inheritance.
 * - xRadius  the horizontal dimension of this oval.
 * - yRadius  the vertical dimension of this oval.
 * Implemented Methods:
 * - All methods in BasicShape by inheritance.
 * - getCopy from Drawable.
 * - All Scalable methods.
 * - Getters for x and y radii.
 * - Equals and HashCode.
 */
public class Oval extends BasicShape {
  private double xRadius;
  private double yRadius;

  /**
   * Sole constructor for Oval.
   * Initializes the name, position, dimensions, and color of this shape.
   *
   * @param name    the name of this shape
   * @param x       the initial x coordinate of this shape
   * @param y       the initial y coordinate of this shape
   * @param xRadius the initial horizontal dimension of this shape.
   * @param yRadius  the initial vertical dimension of this shape.
   * @param color   the initial color of this shape
   */
  public Oval(String name, double x, double y, double xRadius, double yRadius, Color color) {
    super(name, x, y, color);
    if (xRadius < 0 || yRadius < 0) {
      throw new IllegalArgumentException("Cannot have a size less than 0.");
    }
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  @Override
  public Drawable getCopy() {
    Color copy = new Color(color);
    return new Oval(name, posn.getX(), posn.getY(), xRadius, yRadius, copy);
  }

  @Override
  public void stretchHorizontal(double scale) {
    scaleError(scale);
    xRadius *= scale;
  }

  @Override
  public void shrinkHorizontal(double scale) {
    scaleError(scale);
    xRadius /= scale;
  }

  @Override
  public void stretchVertical(double scale) {
    scaleError(scale);
    yRadius *= scale;
  }

  @Override
  public void shrinkVertical(double scale) {
    scaleError(scale);
    yRadius /= scale;
  }

  @Override
  public void scaleUp(double scale) {
    scaleError(scale);
    xRadius *= scale;
    yRadius *= scale;
  }

  @Override
  public void scaleDown(double scale) {
    scaleError(scale);
    xRadius /= scale;
    yRadius /= scale;
  }

  /**
   * Get the horizontal dimension of this oval.
   *
   * @return xRadius the horizontal dimension of this oval
   */
  public double getXRadius() {
    return xRadius;
  }

  /**
   * Get the vertical dimension of this rectangle.
   *
   * @return yRadius the vertical dimension of this oval
   */
  public double getYRadius() {
    return yRadius;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof Oval) {
      Oval r = (Oval) obj;
      return this.name.equals(r.name)
              && this.posn.equals(r.posn)
              && this.color.equals(r.color)
              && this.xRadius - r.xRadius < 0.001
              && this.yRadius - r.yRadius < 0.001;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return name.hashCode() + posn.hashCode() + color.hashCode() + Double.hashCode(xRadius + yRadius);
  }
}
