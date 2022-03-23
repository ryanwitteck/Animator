package controller.commands;

import model.ObjectInterfaces.Drawable;

import model.ObjectInterfaces.Scalable;


/**
 * Command that represents resizing an object over time.
 */
public class ResizeCmd extends GradualCmd {

  private Integer x;
  private Integer y;
  private int destX;
  private int destY;
  private String log;
  private double dx;
  private double dy;


  /**
   * Constructor for GradualCmd.
   *
   * @param obj   the object this command functions on.
   * @param start the tick when this command triggers.
   * @param end   the tick when this command ends.
   */
  public ResizeCmd(Drawable obj, int start, int end, int destX, int destY) {
    super(obj, start, end);
    if (!(obj instanceof Scalable)) {
      throw new IllegalArgumentException("Error: This object is not instance of Scalable");
    }
    this.destX = destX;
    this.destY = destY;
  }

  @Override
  public void execute() {
    super.execute();
    if (x == null || y == null) {
      x = (((Scalable) obj).getXPos());
      y = (((Scalable) obj).getYPos());
      this.log = obj.getName() + " moves from : (" + x + ", " + y + ")" + " to " + "(" + destX +
          ", " + destY + ")" + " from t=" + (startTick - 1) + " to t=" + endTick;
      dx = (destX - x) / (endTick - startTick + 1);
      dy = (destY - y) / (endTick - startTick + 1);
      if (dx > 0) {
        ((Scalable) obj).stretchHorizontal(dx);
        if (dy > 0) {
          ((Scalable) obj).stretchVertical(dy);
        }
        if (dy < 0) {
          ((Scalable) obj).shrinkVertical(dy);
        }
      }
      if (dx < 0) {
        ((Scalable) obj).shrinkHorizontal(dx);
        if (dy > 0) {
          ((Scalable) obj).stretchVertical(dy);
        }
        if (dy < 0) {
          ((Scalable) obj).shrinkVertical(dy);
        }
      }
      if (dx == 0) {
        if (dy > 0) {
          ((Scalable) obj).stretchVertical(dy);
        }
        if (dy < 0) {
          ((Scalable) obj).shrinkVertical(dy);
        }
      }
    }

  }

  @Override
  public String logCmd() {
    if (!running && !complete) {
      throw new IllegalStateException("Error: command has not run");
    }
    return this.log;
  }
}
