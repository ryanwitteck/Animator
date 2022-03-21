package model.ShapeExamples;


import model.Color;
import model.IShape;
import model.Posn;

/**
 * Class that represents a rectangle. May be changed when using swing later on for the view.
 */
public class Rectangle implements IShape {

  private int width;
  private int height;
  private Color color;
  private Posn posn;

  public Rectangle(int x, int y, int width, int height, Color color) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Cannot have a size less than 0.");
    }
    this.width = width;
    this.height = height;
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
}
