package model.commands;

import java.util.List;

import model.interfaces.Drawable;

/**
 * Represents a command to add a Drawable to a list.
 *
 * log format:
 *  - "Create Drawable named [object name] at t=[start tick]"
 */
public class AddShapeCmd extends InstantCmd {

  private final List<Drawable> list;

  /**
   * Constructor for this AddShapeCmd.
   * Takes the target object, start tick, and targeted list as arguments.
   *
   * @param obj  the object to be added to the list.
   * @param tick the tick when this command triggers.
   * @param list the list to add the object to.
   */
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
