package controller.commands;

import model.baseInterfaces.Drawable;

import model.baseInterfaces.Scalable;


/**
 * Represents common attributes for a command that changes an object's dimensions over time.
 */
public abstract class ResizeCmd extends GradualCmd {
  protected double scale;

  /**
   * Constructor for ResizeCmd.
   *
   * @param obj   the object this command functions on.
   * @param start the tick when this command triggers.
   * @param end   the tick when this command ends.
   */
  public ResizeCmd(Drawable obj, int start, int end, double scale) {
    super(obj, start, end);
    if (!(obj instanceof Scalable)) {
      throw new IllegalArgumentException("Error: This object is not instance of Scalable");
    }
    this.scale = scale;
  }
}
