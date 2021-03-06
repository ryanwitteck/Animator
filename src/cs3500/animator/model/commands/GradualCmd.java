package cs3500.animator.model.commands;

import cs3500.animator.model.IAnimation;

/**
 * This abstract class represents a command that occurs over a duration of time.
 * New fields:
 * - running   if this command is running
 * - endTick   the tick this command should stop running
 * - sTick     the tick this command starts running. Used to reset startTick.
 * Implements getEndTick, isRunning, execute, and reset methods.
 */
public abstract class GradualCmd extends ACommand {
  protected boolean running;
  protected int endTick;
  private final int sTick;

  /**
   * Constructor for GradualCmd.
   * Allows the user to decide the target object's name and
   * the period during which this command runs.
   *
   * @param name  the name of the object this command functions on.
   * @param start the tick when this command triggers.
   * @param end   the tick when this command ends.
   * @throws IllegalArgumentException if the end tick is before the start tick.
   */
  public GradualCmd(String name, int start, int end) {
    super(name, start);
    if (end < start) {
      throw new IllegalArgumentException("Error: the end tick must be after the start tick");
    }
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
  public void execute(IAnimation a) {
    super.execute(a);
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
