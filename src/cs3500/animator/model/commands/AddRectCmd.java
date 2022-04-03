package cs3500.animator.model.commands;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.attributes.Posn;
import cs3500.animator.model.shapes.Rectangle;

/**
 * Represents a command to add a Rectangle to an animation.
 * Allows the user to define the parameters of the new Rectangle:
 * its initial position, dimensions, and color.
 * <p>
 * log format:
 * - "Created Rectangle name=[name] posn=[posn] width=[width] height=[height] color=[color]
 *   + " at t=[start tick]"
 */
public class AddRectCmd extends InstantCmd {

  private final Rectangle rect;

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
  public AddRectCmd(String name, double x, double y, double width, double height,
                    Color color, int tick) {
    super(name, tick);
    this.rect = new Rectangle(name, x, y, width, height, color);
  }

  @Override
  public void execute(IAnimation a) {
    super.execute(a);
    a.addDrawable(rect);
  }

  @Override
  public String logCmd() {
    return "Created Rectangle name=" + name + "posn=" + rect.getPos() + "width=" + rect.getWidth()
            + "posn=" + rect.getHeight() + "posn=" + rect.getColor() + " at t=" + startTick;
  }
}
