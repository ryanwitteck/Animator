package cs3500.animator.model.commands;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.shapes.Oval;

/**
 * Represents a command to add an Oval to an animation.
 * Allows the user to define the parameters of the new Oval:
 * its initial position, dimensions, and color.
 * log format:
 * - "Created Oval name = [name] posn = [posn] ry = [XRadius] ry = [yRadius] color = [color]
 *   + " at t = [start tick]"
 */
public class AddOvalCmd extends InstantCmd {

  private final Oval oval;

  /**
   * Sole constructor for AddOvalCmd.
   * Takes the name, position, dimensions, and color of the new oval
   * in addition to the start tick as arguments.
   *
   * @param name    the name of the Oval to be added to the animation.
   * @param x       the initial x coordinate of the new Oval.
   * @param y       the initial y coordinate of the new Oval.
   * @param xRadius the x dimension of the new Oval.
   * @param yRadius the y dimension of the new Oval.
   * @param color   the color of the new Oval.
   * @param tick    the tick when this command triggers.
   * @throws IllegalArgumentException if either given dimension is negative.
   */
  public AddOvalCmd(String name, double x, double y, double xRadius, double yRadius,
                    Color color, int tick) {
    super(name, tick);
    if (xRadius < 0 || yRadius < 0) {
      throw new IllegalArgumentException("Error: dimensions cannot be negative");
    }
    this.oval = new Oval(name, x, y, xRadius, yRadius, color);
  }

  @Override
  public void execute(IAnimation a) {
    super.execute(a);
    a.addDrawable(oval);
  }

  @Override
  public String logCmd() {
    return "Created Oval name = " + name + " posn = " + oval.getPos()
            + " rx = " + oval.getXRadius() + " ry = " + oval.getYRadius()
            + " color = " + oval.getColor() + " at t = " + startTick;
  }
}
