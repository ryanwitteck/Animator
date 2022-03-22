package controller.commands;

import java.util.List;

import model.ObjectInterfaces.Drawable;

// TODO -- document
public class AddShapeCmd extends InstantCmd {
  private List<Drawable> list;

  public AddShapeCmd(Drawable obj, int tick, List<Drawable> list) {
    super(obj, tick);
    this.list = list;
  }

  @Override
  public void execute() {
    super.execute();
    this.list.add(obj);
  }

  @Override
  public String logCmd() {
    return "Create Drawable named " + obj.getName() + " at t=" + startTick;
  }
}
