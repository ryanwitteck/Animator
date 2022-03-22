package model.ShapeExamples;

import java.util.List;

import model.Color;
import model.IShape;
import model.ObjectInterfaces.Drawable;
import model.ObjectInterfaces.Movable;
import model.Posn;

/**
 * Class that represents a shape made of multiple shapes.
 * May be changed when using swing later on for the view.
 */
public class CompoundShape implements IShape {
  private String name;
  private Posn posn;
  private List<IShape> shapes;

  public CompoundShape(String name, int x, int y, List<IShape> shapes) {
    this.name = name;
    this.posn = new Posn(x, y);
    this.shapes = shapes;
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
  public void move(double dx, double dy) {
    this.posn.move(dx, dy);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setColor(int r, int g, int b) {
    //TODO -- Behavior undecided
  }

  @Override
  public void setColor(Color c) {
    //TODO -- Behavior undecided
  }
}
