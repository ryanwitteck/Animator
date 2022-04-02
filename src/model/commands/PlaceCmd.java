package model.commands;

import model.interfaces.Drawable;
import model.interfaces.Movable;
import model.attributes.Posn;

/**
 * Represents a command to place an object at a specified position.
 *
 * log format:
 *  - "[target name] moves from : (x0, y0) to (x1, y1) at t=[start tick]"
 */
public class PlaceCmd extends InstantCmd {

  private Posn startPos;
  private final Posn endPos;

  /**
   * Constructor for PlaceCmd.
   * Takes the target object, start tick, and destination Posn as arguments.
   *
   * @param obj  the object this command functions on.
   * @param tick the tick when this command triggers.
   * @param dest the position we want to place the object.
   */
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
