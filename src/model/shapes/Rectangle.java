package model.shapes;


import model.ObjectInterfaces.Drawable;
import model.attributes.Color;

/**
 * Class that represents a rectangle. May be changed when using swing later on for the view.
 */
public class Rectangle extends BasicShape {
  private int width;
  private int height;

  /**
   *
   * @param name
   * @param x
   * @param y
   * @param width
   * @param height
   * @param color
   */
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
    Color copy = new Color(color);
    return new Rectangle(name, posn.getX(), posn.getY(), width, height, copy);
  }

  @Override
  public void stretchHorizontal(double scale) {
    scaleError(scale);
    width *= scale;
  }

  @Override
  public void stretchVertical(double scale) {
    scaleError(scale);
    height *= scale;
  }

  @Override
  public void scaleUp(double scale) {
    scaleError(scale);
    width *= scale;
    height *= scale;
  }

  @Override
  public void scaleDown(double scale) {
    scaleError(scale);
    width /= scale;
    height /= scale;
  }

  /**
   * Get the width of this rectangle.
   *
   * @return width the width of this rectangle
   */
  public int getWidth() {
    return width;
  }

  /**
   * Get the height of this rectangle.
   *
   * @return height the height of this rectangle
   */
  public int getHeight() {
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
    return name.hashCode() + posn.hashCode() + color.hashCode() + width + height;
  }
}
