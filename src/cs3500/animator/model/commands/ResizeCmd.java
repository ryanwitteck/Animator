package cs3500.animator.model.commands;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.interfaces.Drawable;
import cs3500.animator.model.interfaces.Scalable;

/**
 * This class represents a command that scales an object's dimensions over time to a given value.
 * Implements new fields dx and dy, the change in the target's width and height per tick
 * respectively.
 * log format:
 * - "[target name] scales from : [x0] by [y0] to [x1] by [y1]"
 *   + " from t = [start tick] to t = [end tick]"
 */
public class ResizeCmd extends GradualCmd {
  protected String log;
  protected double dx;
  protected double dy;

  /**
   * Constructor for ResizeCmd.
   * Takes in the name of the target object and start and end tick as arguments.
   *
   * @param name  the name of the object this command functions on.
   * @param start the tick when this command triggers.
   * @param end   the tick when this command ends.
   * @throws IllegalArgumentException if any of the given dimensions are negative.
   */
  public ResizeCmd(String name, int start, int end,
                   double fromX, double fromY, double toX, double toY) {
    super(name, start, end);
    if (fromX < 0 || fromY < 0 || toX < 0 || toY < 0) {
      throw new IllegalArgumentException("Error: dimensions cannot be negative");
    }
    this.dx = (toX - fromX) / (end - start);
    this.dy = (toY - fromY) / (end - start);
    this.log = name + " scales from : " + fromX + " by " + fromY + " to " + toX + " by " + toY
            + " from t = " + start + " to t = " + end;
  }

  @Override
  public void execute(IAnimation a) {
    super.execute(a);
    Drawable target = a.getDrawable(name);
    if (!(target instanceof Scalable)) {
      throw new IllegalArgumentException("Error: This object is not an instance of Scalable");
    }
    ((Scalable) target).addHorizontal(dx);
    ((Scalable) target).addVertical(dy);
  }

  @Override
  public String logCmd() {
    return log;
  }
}
