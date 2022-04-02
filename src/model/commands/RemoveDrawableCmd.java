package model.commands;

import model.IAnimation;

/**
 * Represents a command to remove a Drawable from an animation.
 * <p>
 * log format:
 * - "Removed Drawable named [object name] at t=[start tick]"
 */
public class RemoveDrawableCmd extends InstantCmd {

  /**
   * Sole constructor for RemoveShapeCmd.
   * Takes the name of the target and start tick as arguments.
   *
   * @param name the name of the object to be removed from the animation.
   * @param tick the tick when this command triggers.
   */
  public RemoveDrawableCmd(String name, int tick) {
    super(name, tick);
  }

  @Override
  public void execute(IAnimation a) {
    super.execute(a);
    a.removeDrawable(name);
  }

  @Override
  public String logCmd() {
    return "Removed Drawable named " + name + " at t=" + startTick;
  }
}
