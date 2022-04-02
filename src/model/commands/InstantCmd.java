package model.commands;

import model.IAnimation;

/**
 * This abstract class represents a command that happens instantly (in one tick).
 * Implements getEndTick, execute, reset, and isRunning methods.
 */
public abstract class InstantCmd extends ACommand {

  /**
   * Constructor for InstantCmd.
   * Takes in the target object and execution tick as arguments.
   *
   * @param name the name of the object this command functions on.
   * @param tick the tick when this command triggers.
   */
  public InstantCmd(String name, int tick) {
    super(name, tick);
  }

  @Override
  public int getEndTick() {
    return startTick;
  }

  @Override
  public void execute(IAnimation a) {
    super.execute(a);
    this.complete = true;
  }

  @Override
  public void reset() {
    this.complete = false;
  }

  @Override
  public boolean isRunning() {
    return false;
  }
}
