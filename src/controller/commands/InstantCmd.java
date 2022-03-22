package controller.commands;

import model.ObjectInterfaces.Drawable;

// TODO -- Document
public class InstantCmd extends ACommand {

  public InstantCmd(int tick) {
    super(tick);
  }

  @Override
  public void execute(Drawable obj) {
    super.execute(obj);
    this.complete = true;
  }
}
