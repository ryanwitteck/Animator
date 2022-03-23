package controller.commands;

import model.ObjectInterfaces.Drawable;

// TODO -- document
public abstract class GradualCmd extends ACommand {
  protected boolean running;
  protected int endTick;

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
