package cs3500.animator.model.commands;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.shapes.Oval;

/**
 * Represents a command to add an Oval to an animation.
 * <p>
 * log format:
 * - "Created Oval named [object name] at t=[start tick]"
 */
public class AddOvalCmd extends InstantCmd {

  private final Oval oval;

  /**
   * Sole constructor for AddRectCmd.
   * Takes the name, position, dimensions, and color of the new rectangle
   * in addition to the start tick as arguments.
   *
   * @param name   the name of the rectangle to be added to the animation.
   * @param x      the initial x coordinate of the new Rectangle.
   * @param y      the initial y coordinate of the new Rectangle.
   * @param width  the width of the new Rectangle.
   * @param height the height of the new Rectangle.
   * @param color  the color of the new Rectangle.
   * @param tick   the tick when this command triggers.
   */
  public AddOvalCmd(String name, double x, double y, double width, double height, Color color, int tick) {
    super(name, tick);
    this.oval = new Oval(name, x, y, width, height, color);
  }

  @Override
  public void execute(IAnimation a) {
    super.execute(a);
    a.addDrawable(oval);
  }

  @Override
  public String logCmd() {
    return "Created Oval named " + name + " at t=" + startTick;
  }
}
