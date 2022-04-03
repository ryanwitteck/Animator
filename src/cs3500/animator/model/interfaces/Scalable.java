package cs3500.animator.model.interfaces;

/**
 * Our interface for any object that can be scaled up and down.
 * <p>
 * Every Scalable should...
 * - Be able to scale up or down in both the x and y dimension.
 */
public interface Scalable {

  /**
   * Horizontally stretch this object by the given factor.
   *
   * @param scale the factor to scale this object by
   */
  void stretchHorizontal(float scale);

  /**
   * Horizontally stretch this object by the given factor.
   *
   * @param scale the factor to scale this object by
   */
  void shrinkHorizontal(float scale);


  /**
   * Vertically stretch this object by the given factor.
   *
   * @param scale the factor to scale this object by
   */
  void stretchVertical(float scale);

  /**
   * Vertically shrink this object by the given factor.
   *
   * @param scale the factor to scale this object by
   */
  void shrinkVertical(float scale);


  /**
   * Grow this object by the given factor.
   *
   * @param scale the factor to scale this object by
   */
  void scaleUp(float scale);


  /**
   * Shrink this object by the given factor.
   *
   * @param scale the factor to scale this object by
   */
  void scaleDown(float scale);
}
