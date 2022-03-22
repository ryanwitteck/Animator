package controller.commands;

import model.ObjectInterfaces.Drawable;

// TODO -- Document
public abstract class InstantCmd extends ACommand {

  public InstantCmd(Drawable obj, int tick) {
    super(obj, tick);
  }

  @Override
  public void execute() {
    super.execute();
    this.complete = true;
  }
}
