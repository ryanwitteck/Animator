package model;

import java.util.List;

import model.commands.ICommand;

/**
 * Represents an animated video.
 */
public interface IAnimation {

  /**
   * Execute the command of the function object given to it.
   *
   * @param cmd the function object
   */
  void execute(ICommand cmd);

  /**
   * Get the frame at desired tick.
   *
   * @param tick the desired tick
   * @return the frame
   */
  IFrame getFrame(int tick);

  /**
   * Get the list of frames.
   *
   * @return the list of frames in this animation
   */
  List<IFrame> getFrames();

  /**
   * Get the command log of this animation.
   *
   * @return the command log
   */
  String getCmdLog();
}

