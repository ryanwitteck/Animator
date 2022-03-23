package model.interfaces;

import model.attributes.Posn;

/**
 * Our interface for any object that can be moved.
 */
public interface Movable {

  /**
   * Places this object at the given coordinates.
   */
  void place(double x, double y);

  /**
   * Places this object at the given coordinates.
   *
   * @param p the desired posn
   */
  void place(Posn p);

  /**
   * Moves this object by dx and dy.
   *
   * @param dx the change in x.
   * @param dy the change in y.
   */
  void move(double dx, double dy);

  /**
   * Get the current position of this object.
   *
   * @return the position of this object.
   */
  Posn getPos();
}
