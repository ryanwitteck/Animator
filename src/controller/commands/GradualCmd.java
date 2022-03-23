package controller.commands;

import model.ObjectInterfaces.Drawable;

/**
 * Represents a command that occurs over a duration.
 */
public abstract class GradualCmd extends ACommand {
  protected boolean running;
  protected int endTick;

  /**
   * Constructor for GradualCmd.
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
