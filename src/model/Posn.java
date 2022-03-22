package model;

/**
 * Represents a position on our canvas.
 */
public class Posn {

  //TODO make bounds based on controller specifications.
  private double x;
  private double y;

  /**
   * Our contructor for Posn.
   *
   * @param x the x value
   * @param y the y value
   */
  public Posn(int x, int y) {
    //TODO enforce bounds from controller or assignment specifications.
    this.x = x;
    this.y = y;
  }

  /**
   * Gets this Posn's x value
   *
   * @return the x value
   */
  public double getX() {
    return this.x;
  }

  /**
   * Gets this Posn's y value
   *
   * @return the y value
   */
  public double getY() {
    return this.y;
  }

  /**
   * Changes this Posn's values based on the parameters.
   *
   * @param dx change in x.
   * @param dy change in y.
   * @return newly formed Posn.
   */
  public Posn move(double dx, double dy) {
    this.x += dx;
    this.y += dy;
    return new Posn((int)Math.round(this.x), (int)Math.round(this.y));
  }

  /**
   * Sets this Posn to the given coordinates.
   *
   * @param nx desired x position
   * @param ny desired y position
   */
  public void set(int nx, int ny) {
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
      return this.x == a.x && this.y == a.y;
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
