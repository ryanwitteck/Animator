package model.commands;

import model.interfaces.Drawable;

import model.interfaces.Scalable;


/**
 * Abstract class for commands that scale an object's dimensions over time.
 * Implements constructor exception and field scale.
 */
public abstract class ResizeCmd extends GradualCmd {
  protected double scale;

  /**
   * Constructor for ResizeCmd.
   * Takes in target object and start and end tick as arguments.
   *
   * @param obj   the object this command functions on.
   * @param start the tick when this command triggers.
   * @param end   the tick when this command ends.
   * @throws IllegalArgumentException if the target object is not an instance of scalable.
   */
  public ResizeCmd(Drawable obj, int start, int end, double scale) {
    super(obj, start, end);
    if (!(obj instanceof Scalable)) {
      throw new IllegalArgumentException("Error: This object is not instance of Scalable");
    }
    this.scale = scale;
  }
}
