package controller.commands;

import model.ObjectInterfaces.Drawable;
import model.ObjectInterfaces.Movable;
import model.attributes.Posn;

/**
 * Represents a command to move an object over a period of time.
 */
public class MoveCmd extends GradualCmd {
  private Posn startPos;
  private Posn dest;

  public MoveCmd(Drawable obj, int start, int end, Posn dest) {
    super(obj, start, end);
    if (!(obj instanceof Movable)) {
      throw new IllegalArgumentException("Error: This object is not instance of Movable");
    }
    this.startPos = new Posn(((Movable) obj).getPos());
    this.dest = dest;
  }

  @Override
  public void execute() {
    super.execute();
    double dx = dest.getX() - startPos.getX();
    double dy = dest.getY() - startPos.getY();
    ((Movable) obj).move(dx, dy);
  }

  @Override
  public String logCmd() {
    return obj.getName() + " moves from : " + startPos + " to " + dest
            + " from t=" + startTick + "to t=" + endTick;
  }
}
