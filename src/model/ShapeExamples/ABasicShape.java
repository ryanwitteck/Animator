package model.ShapeExamples;

import model.Color;
import model.IShape;
import model.Posn;

public abstract class ABasicShape implements IShape {
  private Color color;
  private Posn posn;

  public ABasicShape(int x, int y, Color color) {
    this.color = color;
    this.posn = new Posn(x, y);
  }

  @Override
  public void place(int x, int y) {
    this.posn.set(x, y);
  }

  @Override
  public void place(Posn p) {
    this.posn = p;
  }

  @Override
  public void move(int dx, int dy) {
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
