package controller.commands;

import model.IShape;
import model.Posn;

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
  public void execute(IShape s) {
    super.execute(s);
    s.move(dx, dy);
  }
}
