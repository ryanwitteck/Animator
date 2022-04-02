package model.commands;

import model.IAnimation;
import model.interfaces.Drawable;
import model.interfaces.Movable;
import model.attributes.Posn;

/**
 * Represents a command to move an object over a period of time.
 * <p>
 * log format:
 * - "[target name] moves from : (x0, y0) to (x1, y1) from t=[start tick] to t=[end tick]"
 */
public class MoveCmd extends GradualCmd {

  private String log;
  private Posn startPos;
  private final Posn dest;
  private double dx;
  private double dy;

  /**
   * Constructor for MoveCmd.
   * Takes the target object, start and end tick, and destination Posn as arguments.
   *
   * @param name  the name of the object to be added to the list.
   * @param start the tick when this command triggers.
   * @param end   the tick when this command ends.
   * @param dest  the position we want to move the object to.
   */
  public MoveCmd(String name, int start, int end, Posn dest) {
    super(name, start, end);
    this.dest = dest;
  }

  @Override
  public void execute(IAnimation a) {
    super.execute(a);
    Drawable target = a.getDrawable(name);
    if (!(target instanceof Movable)) {
      throw new IllegalArgumentException("Error: This object is not instance of Movable");
    }
    if (startPos == null) {
      startPos = new Posn(((Movable) target).getPos());
      this.log = target.getName() + " moves from : " + startPos + " to " + dest
              + " from t=" + (startTick - 1) + " to t=" + endTick;
      dx = (dest.getX() - startPos.getX()) / (endTick - startTick + 1);
      dy = (dest.getY() - startPos.getY()) / (endTick - startTick + 1);
    }
    ((Movable) target).move(dx, dy);
  }

  @Override
  public String logCmd() {
    if (!running && !complete) {
      throw new IllegalStateException("Error: command has not run");
    }
    return this.log;
  }
}
