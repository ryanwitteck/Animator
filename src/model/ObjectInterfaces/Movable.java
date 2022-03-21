package model.ObjectInterfaces;

import model.Posn;

/**
 * Our interface for any object that can be moved when animated in the future.
 */
public interface Movable {
  /**
   * Places this object at the given coordinates.
   */
  void place(int x, int y);

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
  void move(int dx, int dy);
}
