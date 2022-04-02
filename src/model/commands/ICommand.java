package model.commands;

/**
 * This interface represents a function-object that performs an action.
 * ICommands direct the behavior of our Animations.
 * All commands should execute some action
 * and be able to return the following information:
 *  - the tick they should execute.
 *  - the tick they should stop executing.
 *  - if they have finished running.
 *  - if they are currently running.
 *  - a string representation of itself.
 */
public interface ICommand {

  /**
   * Get the start tick of this command i.e. the tick this command should start running.
   *
   * @return the tick when this command starts.
   */
  int getStartTick();

  /**
   * Get the end tick of this command i.e. the tick this command should finish running.
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
   * Example log: "[target name] moves from (x0, y0) to (x1, y1) at t=[tick]"
   *
   * @return the string representation of running this cmd.
   */
  String logCmd();
}
