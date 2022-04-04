package cs3500.animator.model.commands;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.attributes.Posn;
import cs3500.animator.model.interfaces.Movable;
import cs3500.animator.model.interfaces.Drawable;

/**
 * Represents a command to move an object over a period of time.
 * Requires the user to know the start position of the object and
 * allows the user to decide the end position of the object.
 * log format:
 * - "[target name] moves from : (x0, y0) to (x1, y1) from t=[start tick] to t=[end tick]"
 */
public class MoveCmd extends GradualCmd {

  private final String log;
  private final Posn startPos;
  private Posn currentPos;
  private final double dx; // rate of change of the x value
  private final double dy; // rate of change of the y value

  /**
   * Constructor for MoveCmd.
   * Takes the name of the target object, start and end tick, and destination Posn as arguments.
   *
   * @param name     the name of the object to be added to the list.
   * @param start    the tick when this command triggers.
   * @param end      the tick when this command ends.
   * @param startPos the original position of the target.
   * @param dest     the position we want to move the object to.
   */
  public MoveCmd(String name, int start, int end, Posn startPos, Posn dest) {
    super(name, start, end);
    this.startPos = startPos;
    this.currentPos = new Posn(startPos);
    this.log = name + " moves from : " + startPos + " to " + dest
            + " from t=" + startTick + " to t=" + endTick;
    this.dx = (dest.getX() - startPos.getX()) / (endTick - startTick);
    this.dy = (dest.getY() - startPos.getY()) / (endTick - startTick);
  }

  @Override
  public void execute(IAnimation a) {
    super.execute(a);
    Drawable target = a.getDrawable(name);
    if (!(target instanceof Movable)) {
      throw new IllegalArgumentException("Error: This object is not instance of Movable");
    }
    if (!currentPos.equals(target.getPos())) {
      throw new IllegalStateException("Error: This object is not at the expected position");
    }
    currentPos.move(dx, dy);
    ((Movable) target).move(dx, dy);
  }

  @Override
  public String logCmd() {
    if (!running && !complete) {
      throw new IllegalStateException("Error: command has not run");
    }
    return this.log;
  }

  @Override
  public void reset() {
    super.reset();
    currentPos = new Posn(startPos);
  }
}
