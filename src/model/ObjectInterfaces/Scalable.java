package model.ObjectInterfaces;

/**
 * Our interface for any object that can be scaled when animated in the future.
 */
public interface Scalable {
  /**
   * Horizontally stretch this object by the given factor.
   *
   * @param scale the factor to scale this object by
   */
  void stretchHorizontal(double scale);


  /**
   * Vertically stretch this object by the given factor.
   *
   * @param scale the factor to scale this object by
   */
  void stretchVertical(double scale);


  /**
   * Grow this object by the given factor.
   *
   * @param scale the factor to scale this object by
   */
  void scaleUp(double scale);


  /**
   * Shrink this object by the given factor.
   *
   * @param scale the factor to scale this object by
   */
  void scaleDown(double scale);
}
