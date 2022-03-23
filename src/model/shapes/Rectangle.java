package model.shapes;


import model.ObjectInterfaces.Drawable;
import model.attributes.Color;

/**
 * Class that represents a rectangle. May be changed when using swing later on for the view.
 */
public class Rectangle extends ABasicShape {
  private int width;
  private int height;

  public Rectangle(String name, double x, double y, int width, int height, Color color) {
    super(name, x, y, color);
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Cannot have a size less than 0.");
    }
    this.width = width;
    this.height = height;
  }

  @Override
  public Drawable getCopy() {
    return new Rectangle(name, posn.getX(), posn.getY(), width, height, color);
  }

  @Override
  public void stretchHorizontal(double scale) {
    width *= scale;
  }

  @Override
  public void stretchVertical(double scale) {
    height *= scale;
  }

  @Override
  public void scaleUp(double scale) {
    width *= scale;
    height *= scale;
  }

  @Override
  public void scaleDown(double scale) {
    width /= scale;
    height /= scale;
  }
}
