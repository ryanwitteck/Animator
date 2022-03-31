package model;

import java.util.List;

import model.commands.ICommand;

/**
 * This interface represents an animated video.
 * A video in this context is defined as a collection of IFrames with a clear sequence.
 * IFrames represent the state of the video at a given moment.
 */
public interface IAnimation {

  /**
   * Execute the command of the function object given to it.
   *
   * @param cmd the function object
   */
  void addCommand(ICommand cmd);

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

