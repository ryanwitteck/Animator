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
public class CompoundShape implements IShape {
  private String name;
  private Posn posn;
  private List<IShape> shapes;

  public CompoundShape(String name, double x, double y, List<IShape> shapes) {
    this.name = name;
    this.posn = new Posn(x, y);
    this.shapes = shapes;
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
  public Posn getPos() {
    return posn;
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
