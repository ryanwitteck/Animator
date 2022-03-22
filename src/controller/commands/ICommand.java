package controller.commands;

import model.ObjectInterfaces.Drawable;

/**
 * Represents a function-object that modifies any drawable.
 */
public interface ICommand {
  /**
   * Get the start tick of this command.
   * @return the tick when this command starts.
   */
  public int getStartTick();

  /**
   * Execute this command.
   */
  public void execute();

  /**
   * Get the string representation of running this cmd.
   * @return the string representation of running this cmd.
   */
  public String logCmd();
}
