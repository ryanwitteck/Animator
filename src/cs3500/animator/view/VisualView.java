package cs3500.animator.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JScrollPane;

import cs3500.animator.model.IAnimation;

/**
 * This class represents a visual representation of an animation using swing.
 * This view controls the timing of the video and relies on the SwingViewPanel class to paint
 * each frame.
 * The animation will be rendered as a video in a new window that is opened when renderAnimation is
 * run. This window will also allow the user to scroll through it.
 */
public class VisualView extends JFrame implements AnimationView, ActionListener {

  protected final Timer timer;
  protected final IAnimation animation;
  protected final SwingViewPanel panel;
  protected int tick;

  /**
   * Sole constructor for VisualView. Initializes all fields of this class and sets the
   * parameters of the window that the animation will be animated in.
   * The animation will not start until the renderAnimation method is called.
   *
   * @param windowTitle the title of the animation window
   * @param animation   the animation that this view will visualize
   * @param fps         the framerate of this animation in frames per second
   */
  public VisualView(String windowTitle, IAnimation animation, int fps) {
    super(windowTitle);

    setSize(animation.getWindowWidth() + 10, animation.getWindowHeight() + 40);
    setLocation(0, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.animation = animation;
    this.panel = new SwingViewPanel();
    this.panel.setFrame(animation.getFrame(0));
    this.panel.setPreferredSize(
            new Dimension(10 * animation.getWindowWidth(), 10 * animation.getWindowHeight()));
    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setPreferredSize(
            new Dimension(animation.getWindowWidth(), animation.getWindowHeight()));
    this.add(scrollPane);

    if (fps < 1) {
      fps = 1;
    }
    else if (fps > 1000) {
      fps = 1000;
    }

    this.timer = new Timer(1000 / fps, this);
    tick = 0;
  }

  @Override
  public void renderAnimation() {
    this.setVisible(true);
    tick = 0;
    timer.start();
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    tick++;
    panel.setFrame(animation.getFrame(tick));
    repaint();
    if (tick >= animation.getNFrames() - 1) {
      timer.stop();
    }
  }
}
