package cs3500.animator.model.interfaces;

import cs3500.animator.model.attributes.Posn;

/**
 * Our interface for any object that can be moved.
 * <p>
 * Every Movable should...
 * - Have a position represented by a Posn.
 * - Be able to change position.
 */
public interface Movable {

  /**
   * Places this object at the given coordinates.
   */
  void place(float x, float y);

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
  void move(float dx, float dy);

  /**
   * Get the current position of this object.
   *
   * @return the position of this object.
   */
  Posn getPos();
}
