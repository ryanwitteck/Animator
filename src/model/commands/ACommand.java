package model.commands;

import model.interfaces.Drawable;

/**
 * Represents common attributes and functions among ICommand implementations.
 */
public abstract class ACommand implements ICommand {

  protected Drawable obj;
  protected boolean complete;
  protected int startTick;

  /**
   * Constructor for ACommand.
   *
   * @param obj  the object this command functions on.
   * @param tick the tick when this command triggers.
   */
  public ACommand(Drawable obj, int tick) {
    this.obj = obj;
    this.complete = false;
    this.startTick = tick;
  }

  @Override
  public int getStartTick() {
    if (complete) {
      return -1;
    }
    return startTick;
  }

  @Override
  public boolean isComplete() {
    return complete;
  }

  @Override
  public void execute() {
    if (complete) {
      throw new IllegalStateException("Error: Command already executed");
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof ICommand) {
      ICommand cmd = (ICommand) obj;
      return this.logCmd().equals(cmd.logCmd());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return this.logCmd().hashCode();
  }
}
