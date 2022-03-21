package model;

import java.util.List;

public class FirstAnimation implements IAnimation {

  private final List<IShape> shapes;

  public FirstAnimation(List<IShape> shapes) {
    if (shapes == null) {
      throw new IllegalArgumentException("Cannot have null arguments.");
    }
    this.shapes = shapes;
  }


  @Override
  public void addShape(IShape shape) {
    this.shapes.add(shape);
  }

  @Override
  public void changeColor(int index, Color before, Color after) {
    if (index < 0 || index >= this.shapes.size() || before == null || after == null) {
      throw new IllegalArgumentException("Invalid arguments");
    }

    this.shapes.get(index).setColor(after);
  }

  @Override
  public void changePosition(int index, Posn before, Posn after) {

  }

  @Override
  public void stretchHorizontal(int index, int before, int after) {

  }

  @Override
  public void stretchVertical(int index, int before, int after) {

  }

  @Override
  public void scaleUp(int index, int before, int after) {

  }

  @Override
  public void scaleDown(int index, int before, int after) {

  }
}
