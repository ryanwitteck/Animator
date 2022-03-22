package controller.commands;

import model.ObjectInterfaces.Drawable;

// TODO -- document
public abstract class ACommand implements ICommand {
  protected Drawable obj;
  protected boolean complete;
  protected int startTick;

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
  public void execute() {
    if (complete) {
      throw new IllegalStateException("Error: Command already executed");
    }
  }
}
