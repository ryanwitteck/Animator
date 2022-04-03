package cs3500.animator.model.commands;

/**
 * Abstract class for commands that scale an object's dimensions over time.
 * Implements constructor exception and a new field scale, the factor by which the target's.
 */
public abstract class ResizeCmd extends GradualCmd {
  protected float scale;

  /**
   * Constructor for ResizeCmd.
   * Takes in the name of the target object and start and end tick as arguments.
   *
   * @param name  the name of the object this command functions on.
   * @param start the tick when this command triggers.
   * @param end   the tick when this command ends.
   * @throws IllegalArgumentException if the target object is not an instance of scalable.
   */
  public ResizeCmd(String name, int start, int end, float scale) {
    super(name, start, end);
    this.scale = scale;
  }
}
