package controller.commands;

import model.ObjectInterfaces.Drawable;
import model.ObjectInterfaces.Movable;
import model.attributes.Posn;

/**
 * Represents a command to move an object over a period of time.
 */
public class MoveCmd extends GradualCmd {
  private String log;
  private Posn startPos;
  private Posn dest;
  private double dx;
  private double dy;

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
              + " from t=" + startTick + "to t=" + endTick;
      dx = (dest.getX() - startPos.getX()) / (endTick - startTick);
      dy = (dest.getY() - startPos.getY()) / (endTick - startTick);
    }
    ((Movable) obj).move(dx, dy);
  }

  @Override
  public String logCmd() {
    return this.log;
  }
}
