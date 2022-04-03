package cs3500.animator.model.shapes;

import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.attributes.Posn;

/**
 * Abstract class for IShape.
 * Implements common fields among IShape implementations:
 * - name  the unique name of this shape (required by Drawable).
 * - color the color of this shape (required by Drawable).
 * - posn  the position of this shape (required by Movable).
 * Implements most methods in IShape except for:
 * - the getCopy() method in Drawable.
 * - the Scalable methods.
 */
public abstract class BasicShape implements IShape {
  protected String name;
  protected Color color;
  protected Posn posn;

  /**
   * Constructor for BasicShape.
   *
   * @param name  the name of this shape
   * @param x     the initial x coordinate of this shape
   * @param y     the initial y coordinate of this shape
   * @param color the initial color of this shape
   */
  public BasicShape(String name, float x, float y, Color color) {
    this.name = name;
    this.color = new Color(color);
    this.posn = new Posn(x, y);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Posn getPos() {
    return this.posn;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void place(float x, float y) {
    this.posn.set(x, y);
  }

  @Override
  public void place(Posn p) {
    this.posn = p;
  }

  @Override
  public void move(float dx, float dy) {
    this.posn.move(dx, dy);
  }

  @Override
  public void setColor(float r, float g, float b) {
    this.color.setColor(r, g, b);
  }

  @Override
  public void setColor(Color c) {
    if (c == null) {
      throw new IllegalArgumentException("Cannot have a null color");
    }
    this.color.setColor(c);
  }

  /**
   * If the given scale is negative, throw an exception.
   *
   * @param scale the given scale
   */
  public void scaleError(float scale) {
    if (scale < 0) {
      throw new IllegalArgumentException("Error: Scale cannot be negative");
    }
  }
}
