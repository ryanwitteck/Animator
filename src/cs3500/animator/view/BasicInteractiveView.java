package cs3500.animator.view;

import java.awt.event.ActionEvent;

import cs3500.animator.model.IAnimation;

/**
 * This class represents a visual representation of an animation using swing that the user can
 * interact with. This view controls the timing of the video and relies on the SwingViewPanel
 * class to paint each frame similar to our VisualView implementation.
 * This view also provides the user with the ability to:
 * - Start, pause, resume, and restart the animation.
 * - Enable/disable looping.
 * - Increase or decrease the speed of the animation.
 * The animation will be rendered as a video in a new window that is opened when renderAnimation is
 * run. This window will also allow the user to scroll through it.
 */
public class BasicInteractiveView extends VisualView implements InteractiveView {

  private boolean isLooping;
  private int fps;

  /**
   * Sole constructor for InteractiveView.
   * Initializes all fields of this class and sets the parameters of the window that the animation
   * will be animated in.
   * The window will not appear until the renderAnimation method is called.
   * The animation will start playing automatically after renderAnimation is called.
   *
   * @param windowTitle the title of the animation window
   * @param animation   the animation that this view will visualize
   * @param fps         the framerate of this animation in frames per second
   */
  public BasicInteractiveView(String windowTitle, IAnimation animation, int fps) {
    super(windowTitle, animation, fps);
    if (fps < 1) {
      fps = 1;
    }
    else if (fps > 1000) {
      fps = 1000;
    }
    this.fps = fps;
    this.isLooping = false;
  }

  @Override
  public void play() {
    timer.start();
  }

  @Override
  public void pause() {
    timer.stop();
  }

  @Override
  public boolean isRunning() {
    return timer.isRunning();
  }

  @Override
  public void restart() {
    tick = 0;
    panel.setFrame(animation.getFrame(0));
    repaint();
    timer.start();
  }

  /**
   * Set the fps of the visualization of this animation to the given framerate.
   * This view is limited to a fps of 1000 because of the limits of the swing timer.
   *
   * @param fps the desired framerate
   */
  @Override
  public void setFps(int fps) {
    if (fps < 1 || fps > 1000) {
      throw new IllegalArgumentException("Error: fps must be in range [1, 1000]");
    }
    this.fps = fps;
    this.timer.setDelay(1000 / fps);
  }

  @Override
  public int getFps() {
    return fps;
  }

  @Override
  public void toggleLooping() {
    isLooping = !isLooping;
  }

  @Override
  public boolean getIsLooping() {
    return isLooping;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    tick++;
    if (tick >= animation.getNFrames()) {
      if (isLooping) {
        tick = 0;
      } else {
        timer.stop();
      }
    } else {
      panel.setFrame(animation.getFrame(tick));
      repaint();
    }
  }
}

