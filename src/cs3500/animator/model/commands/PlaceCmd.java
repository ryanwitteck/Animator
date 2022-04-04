package cs3500.animator.model.commands;

import cs3500.animator.model.interfaces.Movable;
import cs3500.animator.model.IAnimation;
import cs3500.animator.model.interfaces.Drawable;
import cs3500.animator.model.attributes.Posn;

/**
 * Represents a command to place an object at a specified position. This command is not 
 * log format:
 * - "[target name] moves from : (x0, y0) to (x1, y1) at t = [start tick]"
 */
public class PlaceCmd extends InstantCmd {

  private final Posn startPos;
  private final Posn endPos;

  /**
   * Constructor for PlaceCmd.
   * Takes the target object, start tick, and destination Posn as arguments.
   *
   * @param name the name of object this command functions on.
   * @param tick the tick when this command triggers.
   * @param dest the position we want to place the object.
   */
  public PlaceCmd(String name, int tick, Posn initPos, Posn dest) {
    super(name, tick);
    this.startPos = new Posn(initPos);
    this.endPos = new Posn(dest);
  }

  @Override
  public void execute(IAnimation a) {
    super.execute(a);
    Drawable target = a.getDrawable(name);
    if (!(target instanceof Movable)) {
      throw new IllegalArgumentException("Error: This object is not instance of Movable");
    }
    if (!startPos.equals(target.getPos())) {
      throw new IllegalStateException("Error: This object is not at the expected position");
    }
    ((Movable) target).place(new Posn(endPos));
  }

  @Override
  public String logCmd() {
    return name + " moves from : " + startPos + " to " + endPos + " at t = " + startTick;
  }
}
