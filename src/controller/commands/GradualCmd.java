package controller.commands;

import model.ObjectInterfaces.Drawable;

// TODO -- document
public class GradualCmd extends ACommand {
  protected int endTick;

  public GradualCmd(int start, int end) {
    super(start);
    this.endTick = end;
  }

  @Override
  public void execute(Drawable obj) {
    super.execute(obj);
    startTick++;
    this.complete = startTick >= endTick;
  }
}
