package controller.commands;

import model.ObjectInterfaces.Drawable;
import model.ObjectInterfaces.Movable;
import model.Posn;

// TODO -- document
public class InstMoveCmd extends InstantCmd {
  private Posn startPos;
  private Posn endPos;

  public InstMoveCmd(Drawable obj, int tick, Posn dest) {
    super(obj, tick);
    if (!(obj instanceof Movable)) {
      throw new IllegalArgumentException("Error: This object is not instance of Movable");
    }
    this.startPos = new Posn(((Movable) obj).getPos());
    this.endPos = dest;
  }

  @Override
  public void execute() {
    super.execute();
    ((Movable) obj).place(endPos);
  }

  @Override
  public String logCmd() {
    return obj.getName() + " moves from : " + startPos + " to " + endPos + " at t=" + startTick;
  }
}
