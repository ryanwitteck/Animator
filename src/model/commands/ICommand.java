package model.commands;

import model.IAnimation;

/**
 * This interface represents a function-object that performs an action.
 * ICommands direct the behavior of our Animations.
 * All commands should execute some action, be able to reset themselves,
 * and be able to return the following information:
 * - the tick it should execute.
 * - the tick it should stop executing.
 * - if it has finished running.
 * - if it is currently running.
 * - a string representation of itself.
 */
public interface ICommand {

  /**
   * Return the start tick of this command i.e. the tick this command should start running.
   *
   * @return the tick when this command starts.
   */
  int getStartTick();

  /**
   * Return the end tick of this command i.e. the tick this command should finish running.
   *
   * @return the tick when this command ends.
   */
  int getEndTick();

  /**
   * Return whether this command is finished running.
   *
   * @return whether this command is finished
   */
  boolean isComplete();

  /**
   * Return whether this command is running.
   *
   * @return whether this command is running
   */
  boolean isRunning();

  /**
   * This method performs some action that alters the state of the animation given to it.
   *
   * @param animation the animation that this motion is a part of.
   */
  void execute(IAnimation animation);

  /**
   * This method resets this command to its pre-execution state.
   */
  void reset();

  /**
   * Return the string representation of running this cmd.
   * Example log: "[target name] moves from (x0, y0) to (x1, y1) at t=[tick]"
   *
   * @return the string representation of running this cmd.
   */
  String logCmd();
}
