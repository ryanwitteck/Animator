package controller.commands;

import model.ObjectInterfaces.Drawable;

// TODO -- document
public abstract class ACommand implements ICommand {
  protected boolean complete;
  protected int startTick;

  public ACommand(int tick) {
    this.complete = false;
    this.startTick = tick;
  }

  @Override
  public int getStartTick() {
    return startTick;
  }

  @Override
  public void execute(Drawable obj) {
    if (complete) {
      throw new IllegalStateException("Error: Command already executed");
    }
  }
}
