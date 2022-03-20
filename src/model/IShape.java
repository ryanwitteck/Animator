package model;

/**
 * Our interface for various shapes that we want to create that may be animated in the future.
 */
public interface IShape {

  /**
   * Makes this object visible if it is not already.
   */
  void makeVisible();

  /**
   * Makes this object invisible if it is not already.
   */
  void makeInvisible();

  /**
   * Places this object at the given coordinates.
   */
  void place(int x, int y);

  /**
   * Places this object at the given coordinates.
   * @param p the desired posn
   */
  void place(Posn p);

  /**
   * Moves this object by dx and dy.
   * @param dx the change in x.
   * @param dy the change in y.
   */
  void move(int dx, int dy);

  /**
   * Changes the color of this object.
   * @param r the red value
   * @param g the green value
   * @param b the blue value
   */
  void setColor(int r, int g, int b);


}
