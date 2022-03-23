package controller.commands;

import model.ObjectInterfaces.Drawable;

/**
 * Represents a command that happens instantly (in one tick).
 */
public abstract class InstantCmd extends ACommand {
  /**
   * Constructor for InstantCmd.
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
