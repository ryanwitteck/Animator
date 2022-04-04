package cs3500.animator.model.shapes;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.interfaces.Drawable;

/**
 * This class represents a shape composed of multiple shapes.
 * May be changed when using swing later on for the view.
 * Contains fields:
 * - All fields in BasicShape by inheritance.
 * - shapes  the list of shapes that form this shape.
 * Implemented Methods:
 * - All methods in BasicShape by inheritance.
 * - getCopy from Drawable.
 * - All Scalable methods.
 * - Getters for width and height.
 * - Equals and HashCode.
 */
public class CompoundShape extends BasicShape {
  private final List<IShape> shapes;

  /**
   * Constructor for CompoundShape.
   *
   * @param name   the name of this shape
   * @param x      the initial x coordinate of this shape
   * @param y      the initial y coordinate of this shape
   * @param shapes the shapes that define this shape
   */
  public CompoundShape(String name, double x, double y, List<IShape> shapes) {
    super(name, x, y, new Color(0, 0, 0));
    this.shapes = shapes;
  }

  @Override
  public Drawable getCopy() {
    List<IShape> copy = new ArrayList<>();
    for (IShape s : shapes) {
      copy.add((IShape) s.getCopy());
    }

    return new CompoundShape(name, posn.getX(), posn.getY(), copy);
  }

  @Override
  public void setColor(double r, double g, double b) {
    throw new IllegalStateException("Error: this object does not support this operation");
  }

  @Override
  public void setColor(Color c) {
    throw new IllegalStateException("Error: this object does not support this operation");
  }

  @Override
  public void stretchHorizontal(double scale) {
    scaleError(scale);
    for (IShape s : shapes) {
      s.stretchHorizontal(scale);
    }
  }

  @Override
  public void addHorizontal(double dx) {
    for (IShape s : shapes) {
      s.addHorizontal(dx);
    }
  }

  @Override
  public void stretchVertical(double scale) {
    scaleError(scale);
    for (IShape s : shapes) {
      s.stretchVertical(scale);
    }
  }

  @Override
  public void addVertical(double dy) {
    for (IShape s : shapes) {
      s.addVertical(dy);
    }
  }

  /**
   * Get the list of shapes this object is made from.
   *
   * @return shapes the list of shapes defining this object.
   */
  public List<IShape> getShapes() {
    List<IShape> copy = new ArrayList<>();
    for (IShape s : shapes) {
      copy.add((IShape) s.getCopy());
    }

    return copy;
  }
}
