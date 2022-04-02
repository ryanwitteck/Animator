package model.commands;

import model.IAnimation;
import model.attributes.Color;
import model.shapes.Rectangle;

/**
 * Represents a command to add a Drawable to an animation.
 * <p>
 * log format:
 * - "Created Rectangle named [object name] at t=[start tick]"
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
  public AddRectCmd(String name, double x, double y, int width, int height, Color color, int tick) {
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
    return "Created Rectangle named " + name + " at t=" + startTick;
  }
}
