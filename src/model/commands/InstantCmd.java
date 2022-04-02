package model.commands;

import model.interfaces.Drawable;

/**
 * This abstract class represents a command that happens instantly (in one tick).
 * Implements getEndTick, execute, and isRunning methods.
 */
public abstract class InstantCmd extends ACommand {

  /**
   * Constructor for InstantCmd.
   * Takes in the target object and execution tick as arguments.
   *
   * @param obj  the object this command functions on.
   * @param tick the tick when this command triggers.
   */
  public InstantCmd(Drawable obj, int tick) {
    super(obj, tick);
  }

  @Override
  public int getEndTick() {
    return startTick;
  }

  @Override
  public void execute() {
    super.execute();
    this.complete = true;
  }

  @Override
  public boolean isRunning() {
    return false;
  }
}
