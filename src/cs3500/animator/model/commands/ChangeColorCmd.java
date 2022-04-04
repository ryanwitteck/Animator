package cs3500.animator.model.commands;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.interfaces.Drawable;

/**
 * Represents a command to change the color of an object over a period of time.
 * Requires the user to know the initial color of the object and
 * allows the user to decide the final color of the object.
 * log format:
 * - "[target name] changes color from : (r0, g0, g0) to (r1, g1, b1) "
 * + "from t=[start tick] to t=[end tick]"
 */
public class ChangeColorCmd extends GradualCmd {

  private final String log;
  private final Color initColor; // initial color of the object. Used to reset this cmd.
  private Color startColor;
  private final double dr; // rate of change of the red value
  private final double dg; // rate of change of the green value
  private final double db; // rate of change of the blue value

  /**
   * Constructor for ChangeColorCmd.
   * Takes the name of the target object, start and end tick, and final Color as arguments.
   *
   * @param name       the name of the object to be added to the list.
   * @param start      the tick when this command triggers.
   * @param end        the tick when this command ends.
   * @param startColor the original color of this object.
   * @param endColor   the color we want this object to become.
   */
  public ChangeColorCmd(String name, int start, int end, Color startColor, Color endColor) {
    super(name, start, end);
    this.initColor = startColor;
    this.startColor = new Color(startColor);
    this.log = name + " changes color from : " + initColor + " to " + endColor
            + " from t = " + (startTick - 1) + " to t = " + endTick;
    this.dr = (endColor.getR() - initColor.getR()) / (endTick - startTick + 1);
    this.dg = (endColor.getG() - initColor.getG()) / (endTick - startTick + 1);
    this.db = (endColor.getB() - initColor.getB()) / (endTick - startTick + 1);
  }

  @Override
  public void execute(IAnimation a) {
    super.execute(a);
    Drawable target = a.getDrawable(name);
    if (!target.getColor().equals(startColor)) {
      throw new IllegalStateException("Error: the target is not the expected color");
    }
    startColor.changeColor(dr, dg, db);
    target.setColor(startColor);
  }

  @Override
  public String logCmd() {
    return this.log;
  }

  @Override
  public void reset() {
    super.reset();
    this.startColor = new Color(initColor);
  }
}
