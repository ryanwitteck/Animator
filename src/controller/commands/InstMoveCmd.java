package controller.commands;

import model.ObjectInterfaces.Drawable;
import model.ObjectInterfaces.Movable;

// TODO -- document
public class InstMoveCmd extends InstantCmd {
  private double dx;
  private double dy;

  public InstMoveCmd(int tick, double dx, double dy) {
    super(tick);
    this.dx = dx;
    this.dy = dy;
  }

  @Override
  public void execute(Drawable obj) {
    super.execute(obj);
    if (obj instanceof Movable) {
      ((Movable)obj).move(dx, dy);
    }
    else {
      throw new IllegalArgumentException("Error: This object is not instance of Movable");
    }
  }
}
