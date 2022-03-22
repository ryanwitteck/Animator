package controller.commands;

import model.ObjectInterfaces.Drawable;

// TODO -- document
public abstract class GradualCmd extends ACommand {
  protected int endTick;

  public GradualCmd(Drawable obj, int start, int end) {
    super(obj, start);
    this.endTick = end;
  }

  @Override
  public void execute() {
    super.execute();
    startTick++;
    this.complete = startTick >= endTick;
  }
}
