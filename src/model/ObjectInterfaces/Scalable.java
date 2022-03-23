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
   * Horizontally stretch this object by the given factor.
   *
   * @param scale the factor to scale this object by
   */
  void shrinkHorizontal(double scale);


  /**
   * Vertically stretch this object by the given factor.
   *
   * @param scale the factor to scale this object by
   */
  void stretchVertical(double scale);

  /**
   * Vertically shrink this object by the given factor.
   *
   * @param scale the factor to scale this object by
   */
  void shrinkVertical(double scale);


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

  /**
   * Gets the current x position.
   */
  int getXPos();

  /**
   * Gets the current y position.
   */
  int getYPos();
}
