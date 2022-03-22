package controller.commands;

import model.IShape;

// TODO -- Document
public class InstantCmd extends ACommand {

  public InstantCmd(int tick) {
    super(tick);
  }

  @Override
  public void execute(IShape s) {
    super.execute(s);
    this.complete = true;
  }
}
