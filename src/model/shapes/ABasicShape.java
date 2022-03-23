package model.shapes;

import model.attributes.Color;
import model.attributes.Posn;

public abstract class ABasicShape implements IShape {
  protected String name;
  protected Color color;
  protected Posn posn;

  public ABasicShape(String name, double x, double y, Color color) {
    this.name = name;
    this.color = color;
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
    this.color = new Color(r, g, b);
  }

  @Override
  public void setColor(Color c) {
    if (c == null) {
      throw new IllegalArgumentException("Cannot have a null color");
    }
    this.color = c;
  }
}
