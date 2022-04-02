package model.commands;

import model.interfaces.Drawable;

/**
 * This abstract class represents a command that occurs over a duration of time.
 * New fields:
 *  - running   if this command is running
 *  - endTick   the tick this command should stop running
 *  - sTick     the tick this command starts running. Used to reset startTick.
 * Implements getEndTick, isRunning, execute, and reset methods.
 */
public abstract class GradualCmd extends ACommand {
  protected boolean running;
  protected int endTick;
  private int sTick;

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
    this.sTick = start;
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

  @Override
  public void reset() {
    startTick = sTick;
    complete = false;
    running = false;
  }
}
