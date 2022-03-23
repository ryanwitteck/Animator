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
   * Get the end tick of this command.
   * @return the tick when this command ends.
   */
  public int getEndTick();

  /**
   * Get whether this command is finished running.
   * @return whether this command is finished
   */
  public boolean isComplete();

  /**
   * Get whether this command is running.
   * @return whether this command is running
   */
  public boolean isRunning();

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
