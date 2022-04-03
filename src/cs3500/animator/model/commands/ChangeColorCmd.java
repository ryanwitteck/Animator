package cs3500.animator.model.commands;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.interfaces.Drawable;

/**
 * Represents a command to change the color of an object over a period of time.
 * <p>
 * log format:
 * - "[target name] changes color from : (r0, g0, g0) to (r1, g1, b1) "
 * + "from t=[start tick] to t=[end tick]"
 */
public class ChangeColorCmd extends GradualCmd {

  private String log;
  private Color initColor; // initial color of the object. Used to reset this cmd.
  private Color startColor;
  private Color endColor;
  private double dr; // rate of change of the red value
  private double dg; // rate of change of the green value
  private double db; // rate of change of the blue value

  /**
   * Constructor for ChangeColorCmd.
   * Takes the name of the target object, start and end tick, and final Color as arguments.
   *
   * @param name       the name of the object to be added to the list.
   * @param start      the tick when this command triggers.
   * @param end        the tick when this command ends.
   * @param endColor   the color we want this object to become.
   */
  public ChangeColorCmd(String name, int start, int end, Color endColor) {
    super(name, start, end);
    this.endColor = endColor;
  }

  @Override
  public void execute(IAnimation a) {
    super.execute(a);
    Drawable target = a.getDrawable(name);
    if (initColor == null) {
      initColor = new Color(target.getColor());
      this.log = name + " changes color from : " + initColor + " to " + endColor
              + " from t=" + (startTick - 1) + " to t=" + endTick;
      dr = (endColor.getR() - initColor.getR()) / (endTick - startTick + 1);
      dg = (endColor.getG() - initColor.getG()) / (endTick - startTick + 1);
      db = (endColor.getB() - initColor.getB()) / (endTick - startTick + 1);
      startColor = new Color(initColor);
    }
    startColor.changeColor(dr, dg, db);
    target.setColor(startColor);
  }

  @Override
  public String logCmd() {
    if (!running && !complete) {
      throw new IllegalStateException("Error: command has not run");
    }
    return this.log;
  }

  @Override
  public void reset() {
    super.reset();
  }
}
