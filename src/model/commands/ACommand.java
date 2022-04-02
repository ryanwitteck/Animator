package model.commands;

import model.IAnimation;

/**
 * Abstract class for ICommand interface.
 * Implements the getStartTick, isComplete, and execute methods in the ICommand interface.
 */
public abstract class ACommand implements ICommand {

  protected String name;
  protected int startTick;
  protected boolean complete;


  /**
   * Sole constructor for ACommand.
   * Takes in basic information -- the name of the target object and the start tick -- as arguments.
   * Initializes complete to false;
   *
   * @param name  the name of the object this command functions on.
   * @param tick  the tick when this command triggers.
   */
  public ACommand(String name, int tick) {
    this.name = name;
    this.startTick = tick;
    this.complete = false;
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
  public void execute(IAnimation a) {
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
