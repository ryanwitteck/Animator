package model;

import java.util.List;

import model.commands.ICommand;
import model.interfaces.Drawable;

/**
 * The IAnimation interface represents our model of an animated video.
 * A video in this context is defined as a collection of IFrames with a clear sequence,
 * whose motions (any changes between IFrames) are directed by various ICommands.
 * Each IFrame represents the state of the video at a given moment and
 * each ICommand represents some motion in the video.
 * All IAnimations must:
 * - Be able to accept new commands.
 * - Be able to remove commands.
 * - Be able to compile the animation as a collection of frames.
 * - Return the IFrame at a given tick.
 * - Return all the IFrames in the video as a list.
 * - Return the string representation of every ICommand that directs this animation,
 * referred to as the command log.
 */
public interface IAnimation {

  /**
   * Add the given command/motion to this animation.
   * Even after using this method to add a cmd to the animation, there will not be any
   * change until the compile method is called.
   *
   * @param cmd the desired command
   */
  void addCmd(ICommand cmd);

  /**
   * Remove the given command/motion from this animation.
   * Even after using this method to remove a cmd to the animation, there will not be any
   * change until the compile method is called.
   *
   * @param cmd the desired command
   */
  void removeCmd(ICommand cmd);

  /**
   * Add the given Drawable to this animation.
   * Even after using this method to add a Drawable to the animation, there will not be any
   * change until the compile method is called.
   *
   * @param d the desired Drawable
   */
  void addDrawable(Drawable d);

  /**
   * Remove the given Drawable from this animation.
   * Even after using this method to remove a Drawable from the animation, there will not be any
   * change until the compile method is called.
   *
   * @param name the name of the desired Drawable
   */
  void removeDrawable(String name);

  /**
   * Return the Drawable in this animation with the given name.
   * Mostly meant for use by ICommands. May fail if the compile method has not run.
   *
   * @param name the name of the desired Drawable
   */
  Drawable getDrawable(String name);

  /**
   * This method will initialize all the frames in the animation according to how it is defined
   * by the ICommands given to it. This method must be run or else the any changes to the animation
   * will not be reflected in the animation itself.
   */
  void compile();

  /**
   * Returns the frame of this animation at the desired tick.
   * Using the frame returned by this method, the receiver should be able to create a visualization
   * of this animation at the given tick or otherwise analyse the state of this animation at the
   * given tick.
   * May not return the expected frame if compile has not been run since the last change.
   *
   * @param tick the desired tick
   * @return the frame at the given tick
   */
  IFrame getFrame(int tick);

  /**
   * Returns all the frames of this animation in the form of a list.
   * Using this list of frames, the receiver should be able to create a complete visualization of
   * this animation or otherwise analyze the state of this animation at every tick.
   * May not return the expected list if compile has not been run since the last change.
   *
   * @return the list of frames in this animation
   */
  List<IFrame> getFrames();

  /**
   * Returns the command log of this animation.
   * The command log is the string representation of every ICommand that is part of this
   * animation. The log should be readable and contain all the information necessary to
   * understand the behavior of this animation.
   * May not return the expected log if compile has not been run since the last change.
   *
   * @return the command log
   */
  String getCmdLog();
}

