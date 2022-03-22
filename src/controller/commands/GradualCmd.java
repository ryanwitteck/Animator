package controller.commands;

import model.IShape;

// TODO -- document
public class GradualCmd extends ACommand {
  protected int endTick;

  public GradualCmd(int start, int end) {
    super(start);
    this.endTick = end;
  }

  @Override
  public void execute(IShape s) {
    super.execute(s);
    startTick++;
    this.complete = startTick >= endTick;
  }
}
