package model.ShapeExamples;


import model.Color;
import model.IShape;
import model.Posn;

/**
 * Class that represents a rectangle. May be changed when using swing later on for the view.
 */
public class Rectangle extends ABasicShape {
  private int width;
  private int height;

  public Rectangle(int x, int y, int width, int height, Color color) {
    super(x, y, color);
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Cannot have a size less than 0.");
    }
    this.width = width;
    this.height = height;
  }
}
