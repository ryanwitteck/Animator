package controller.commands;

import model.IShape;

/**
 * Represents a function-object that modifies a shape.
 */
public interface ICommand {
  /**
   * Get the start tick of this command.
   * @return the tick when this command starts.
   */
  public int getStartTick();

  /**
   * Execute this command on the given IShape.
   * @param s The given shape.
   */
  public void execute(IShape s);
}
