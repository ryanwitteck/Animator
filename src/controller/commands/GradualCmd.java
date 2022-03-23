package controller.commands;

import model.ObjectInterfaces.Drawable;

// TODO -- document
public abstract class GradualCmd extends ACommand {
  private boolean isRunning;
  protected int endTick;

  public GradualCmd(Drawable obj, int start, int end) {
    super(obj, start);
    this.isRunning = false;
    this.endTick = end;
  }

  @Override
  public int getEndTick() {
    return endTick;
  }

  @Override
  public boolean isRunning() {
    return isRunning;
  }

  @Override
  public void execute() {
    super.execute();
    startTick++;
    complete = startTick >= endTick;
    isRunning = !complete;
  }
}
