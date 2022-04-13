package cs3500.animator.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

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
 * run. This window will also allow the user to scroll through it (TODO implement scrolling).
 */
public class BasicInteractiveView extends JFrame implements InteractiveView, ActionListener {

  private final Timer timer;
  private final IAnimation animation;
  private final SwingViewPanel panel;
  private int fps;
  private int tick;
  private boolean isLooping;

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
    super(windowTitle);

    setSize(animation.getWindowWidth(), animation.getWindowHeight());
    setLocation(0, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.animation = animation;
    this.timer = new Timer(1000 / fps, this);
    this.fps = fps;
    this.tick = 0;
    this.isLooping = false;

    //main panel
    JPanel mainPanel = new JPanel();
    add(mainPanel);

    //animation panel with a scrollbar
    this.panel = new SwingViewPanel();
    this.panel.setFrame(animation.getFrame(0));
    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setPreferredSize(
            new Dimension(animation.getWindowWidth(), animation.getWindowHeight()));
    mainPanel.add(scrollPane);
  }

  @Override
  public void renderAnimation() {
    this.setVisible(true);
    repaint();
    timer.start();
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

