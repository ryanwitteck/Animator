package model.shapes;

import model.attributes.Color;
import model.attributes.Posn;

/**
 * Represents common attributes and functions among IShape implementations.
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
  public BasicShape(String name, double x, double y, Color color) {
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
  public void place(double x, double y) {
    this.posn.set(x, y);
  }

  @Override
  public void place(Posn p) {
    this.posn = p;
  }

  @Override
  public void move(double dx, double dy) {
    this.posn.move(dx, dy);
  }

  @Override
  public void setColor(int r, int g, int b) {
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
  public void scaleError(double scale) {
    if (scale < 0) {
      throw new IllegalArgumentException("Error: Scale cannot be negative");
    }
  }
}
