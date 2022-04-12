package cs3500.animator.view;

/**
 * This interface represents a visualization of an animation that the user can interact with.
 * This interface extends our AnimationView interface and provides additional controls to the user.
 * Implementations of this interface should allow the user to:
 * - Start, pause, resume, and restart the animation.
 * - Enable/disable looping.
 * - Increase or decrease the speed of the animation.
 */
public interface InteractiveView extends AnimationView {
  /**
   * This method tells this view to start to updating the current tick of this animation at the
   * rate of the current fps.
   */
  void play();

  /**
   * This method stops this view from updating the current tick of this animation.
   */
  void pause();

  /**
   * Returns if the view is paused or playing i.e. if the current tick is being incremented.
   *
   * @return whether this animation is or isn't playing
   */
  boolean isRunning();

  /**
   * This method resets this view to the first frame of the animation being visualized.
   * Depending on the implementation, the visualization may pause itself after resetting.
   */
  void restart();

  /**
   * Set the fps of the visualization of this animation to the given framerate.
   *
   * @param fps the desired framerate
   */
  void setFps(int fps);

  /**
   * Return the framerate of this view.
   *
   * @return the frames per second this animation is being played in
   */
  int getFps();

  /**
   * Toggles if this view loops itself after completing the animation. If looping is on, this view
   * will reset the animation after it reaches its end; meanwhile if looping is off, the view should
   * end on the final frame of the animation.
   */
  void toggleLooping();

  /**
   * Returns if this view is set to loop the animation.
   *
   * @return whether this view is looping
   */
  boolean getIsLooping();
}
