package controller.commands;

import model.base_interfaces.Drawable;
import model.base_interfaces.Movable;
import model.attributes.Posn;

/**
 * Represents a command to move an object over a period of time.
 */
public class MoveCmd extends GradualCmd {

  private String log;
  private Posn startPos;
  private final Posn dest;
  private double dx;
  private double dy;

  /**
   * Constructor for MoveCmd.
   *
   * @param obj   the object to be added to the list.
   * @param start the tick when this command triggers.
   * @param end   the tick when this command ends.
   * @param dest  the position we want to move the object to.
   */
  public MoveCmd(Drawable obj, int start, int end, Posn dest) {
    super(obj, start, end);
    if (!(obj instanceof Movable)) {
      throw new IllegalArgumentException("Error: This object is not instance of Movable");
    }
    this.dest = dest;
  }

  @Override
  public void execute() {
    super.execute();
    if (startPos == null) {
      startPos = new Posn(((Movable) obj).getPos());
      this.log = obj.getName() + " moves from : " + startPos + " to " + dest
          + " from t=" + (startTick - 1) + " to t=" + endTick;
      dx = (dest.getX() - startPos.getX()) / (endTick - startTick + 1);
      dy = (dest.getY() - startPos.getY()) / (endTick - startTick + 1);
    }
    ((Movable) obj).move(dx, dy);
  }

  @Override
  public String logCmd() {
    if (!running && !complete) {
      throw new IllegalStateException("Error: command has not run");
    }
    return this.log;
  }
}
