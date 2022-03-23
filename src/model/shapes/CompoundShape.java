package model.shapes;

import java.util.ArrayList;
import java.util.List;

import model.ObjectInterfaces.Drawable;
import model.attributes.Color;
import model.attributes.Posn;

/**
 * Class that represents a shape made of multiple shapes.
 * May be changed when using swing later on for the view.
 */
public class CompoundShape extends ABasicShape {
  private List<IShape> shapes;

  public CompoundShape(String name, double x, double y, List<IShape> shapes) {
    super(name, x, y, new Color(0, 0, 0));
    this.shapes = shapes;
  }

  @Override
  public Drawable getCopy() {
    List<IShape> copy = new ArrayList<IShape>();
    for (IShape s : shapes) {
      copy.add((IShape)s.getCopy());
    }

    return new CompoundShape(name, posn.getX(), posn.getY(), copy);
  }

  @Override
  public void setColor(int r, int g, int b) {
    throw new IllegalStateException("Error: this object does not support this operation");
  }

  @Override
  public void setColor(Color c) {
    throw new IllegalStateException("Error: this object does not support this operation");
  }

  @Override
  public void stretchHorizontal(double scale) {
    for (IShape s : shapes) {
      s.stretchHorizontal(scale);
    }
  }

  @Override
  public void stretchVertical(double scale) {
    for (IShape s : shapes) {
      s.stretchVertical(scale);
    }
  }

  @Override
  public void scaleUp(double scale) {
    for (IShape s : shapes) {
      s.scaleUp(scale);
    }
  }

  @Override
  public void scaleDown(double scale) {
    for (IShape s : shapes) {
      s.scaleDown(scale);
    }
  }
}
