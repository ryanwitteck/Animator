package model;

public interface IAnimation {
  // Add shape(instant - all attributes to be created),
  // change color, change position, change dimensions

  /**
   * Adds a shape to the canvas at the current tick.
   *
   * @param shape the desired shape.
   */
  void addShape(IShape shape);

  /**
   * Changes the color of the shape at the desired point in the list.
   *
   * @param index  index of the shape in the list of shapes.
   * @param before the color before the change.
   * @param after  the color after the change.
   */
  void changeColor(int index, Color before, Color after);

  /**
   * Changes the position of the shape at the desired point in the list.
   *
   * @param index  index of the shape
   * @param before the posn before
   * @param after  the posn after
   */
  void changePosition(int index, Posn before, Posn after);

  void stretchHorizontal(int index, int before, int after);

  void stretchVertical(int index, int before, int after);


  void scaleUp(int index, int before, int after);

  void scaleDown(int index, int before, int after);
}

