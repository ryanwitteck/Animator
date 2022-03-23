package controller.commands;

/**
 * Represents a function-object that modifies any drawable.
 */
public interface ICommand {
  /**
   * Get the start tick of this command.
   *
   * @return the tick when this command starts.
   */
  int getStartTick();

  /**
   * Get the end tick of this command.
   *
   * @return the tick when this command ends.
   */
  int getEndTick();

  /**
   * Get whether this command is finished running.
   *
   * @return whether this command is finished
   */
  boolean isComplete();

  /**
   * Get whether this command is running.
   *
   * @return whether this command is running
   */
  boolean isRunning();

  /**
   * Execute this command.
   */
  void execute();

  /**
   * Get the string representation of running this cmd.
   *
   * @return the string representation of running this cmd.
   */
  String logCmd();
}
