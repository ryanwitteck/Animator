package model.attributes;

/**
 * Represents a position on our canvas.
 * Our coordinate system:
 *  - The position (0, 0) corresponds to the top left corner of our canvas.
 *  - The x-value increases as we move rightward on the canvas.
 *  - The y-value increases as we move downward on the canvas.
 */
public class Posn {

  //TODO make bounds based on controller specifications.
  private double x;
  private double y;

  /**
   * Our constructor for Posn taking two doubles as arguments.
   * Initializes the x and y coordinate to the given double values.
   *
   * @param x the x value
   * @param y the y value
   */
  public Posn(double x, double y) {
    //TODO enforce bounds from controller or assignment specifications.
    this.x = x;
    this.y = y;
  }

  /**
   * Our constructor for Posn taking another Posn as arguments.
   * Initializes the x and y coordinate to the given Posn's x and y coordinates.
   *
   * @param posn the position to copy
   */
  public Posn(Posn posn) {
    //TODO enforce bounds from controller or assignment specifications.
    this.x = posn.x;
    this.y = posn.y;
  }

  /**
   * Gets this Posn's x value.
   *
   * @return the x value
   */
  public double getX() {
    return this.x;
  }

  /**
   * Gets this Posn's y value.
   *
   * @return the y value
   */
  public double getY() {
    return this.y;
  }

  /**
   * Changes this Posn's values based on the given double values.
   *
   * @param dx change in x.
   * @param dy change in y.
   */
  public void move(double dx, double dy) {
    this.x += dx;
    this.y += dy;
  }

  /**
   * Sets this Posn to the given coordinates.
   *
   * @param nx desired x position
   * @param ny desired y position
   */
  public void set(double nx, double ny) {
    this.x = nx;
    this.y = ny;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other instanceof Posn) {
      Posn a = (Posn) other;
      return Math.abs(this.x - a.x) < 0.001 && Math.abs(this.y - a.y) < 0.001;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Double.hashCode(x + y);
  }

  @Override
  public String toString() {
    return "( " + this.x + ", " + this.y + " )";
  }
}
