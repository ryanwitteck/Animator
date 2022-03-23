package controller.commands;

import model.ObjectInterfaces.Drawable;
import model.ObjectInterfaces.Movable;
import model.attributes.Posn;

// TODO -- document
public class PlaceCmd extends InstantCmd {
  private Posn startPos;
  private Posn endPos;

  public PlaceCmd(Drawable obj, int tick, Posn dest) {
    super(obj, tick);
    if (!(obj instanceof Movable)) {
      throw new IllegalArgumentException("Error: This object is not instance of Movable");
    }
    this.endPos = new Posn(dest);
  }

  @Override
  public void execute() {
    super.execute();
    this.startPos = new Posn(((Movable) obj).getPos());
    ((Movable) obj).place(new Posn(endPos));
  }

  @Override
  public String logCmd() {
    if (!complete) {
      throw new IllegalStateException("Error: command has not run");
    }
    return obj.getName() + " moves from : " + startPos + " to " + endPos + " at t=" + startTick;
  }
}
