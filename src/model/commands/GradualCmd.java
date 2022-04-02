package model.commands;

import model.interfaces.Drawable;

/**
 * This abstract class represents a command that occurs over a duration of time.
 * Defines new fields running and endTick. Implements getEndTick, isRunning, and execute methods.
 */
public abstract class GradualCmd extends ACommand {
  protected boolean running;
  protected int endTick;

  /**
   * Constructor for GradualCmd.
   * Takes in the target object and
   * this command's start and end tick as arguments.
   *
   * @param obj   the object this command functions on.
   * @param start the tick when this command triggers.
   * @param end   the tick when this command ends.
   */
  public GradualCmd(Drawable obj, int start, int end) {
    super(obj, start);
    this.running = false;
    this.endTick = end;
  }

  @Override
  public int getEndTick() {
    return endTick;
  }

  @Override
  public boolean isRunning() {
    return running;
  }

  @Override
  public void execute() {
    super.execute();
    startTick++;
    complete = startTick >= endTick;
    running = !complete;
  }
}
