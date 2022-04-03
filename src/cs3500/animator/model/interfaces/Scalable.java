package cs3500.animator.model.interfaces;

/**
 * Our interface for any object that can be scaled up and down.
 * <p>
 * Every Scalable should...
 * - Be able to change its horizontal and vertical dimensions.
 */
public interface Scalable {

  /**
   * Horizontally stretch this object by the given factor.
   *
   * @param scale the factor to scale this object by
   */
  void stretchHorizontal(double scale);

  /**
   * Increase this object's width (the term used to refer to a shape's x dimension may vary)
   * by the given value.
   *
   * @param dx the change in width.
   */
  void addHorizontal(double dx);

  /**
   * Vertically stretch this object by the given factor.
   *
   * @param scale the factor to scale this object by
   */
  void stretchVertical(double scale);

  /**
   * Increase this object's height (the term used to refer to a shape's x dimension may vary)
   * by the given value.
   *
   * @param dy the change in height.
   */
  void addVertical(double dy);
}
