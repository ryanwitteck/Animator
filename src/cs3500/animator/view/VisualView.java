package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs3500.animator.model.IAnimation;

/**
 * This class represents a visual representation of an animation using swing.
 * This view controls the timing of the video and relies on the SwingViewPanel class to paint
 * each frame.
 * The animation will be rendered as a video in a new window that is opened when renderAnimation is
 * run. This window will also allow the user to scroll through it.
 */
public class VisualView extends JFrame implements AnimationView {

  private final Timer timer;
  private final IAnimation animation;
  private final SwingViewPanel panel;
  private int tick;

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
    this.timer = new Timer(1000 / fps, new MyActionListener());
    tick = 0;
  }

  @Override
  public void renderAnimation() {
    this.setVisible(true);
    tick = 0;
    timer.start();
  }

  /**
   * This ActionListener class triggers every 1000 / fps milliseconds. Every time it triggers,
   * it increments the tick and updates the visuals of this view. When the animation runs out of
   * frames, this class stops the timer and the window stops changing.
   */
  private class MyActionListener implements ActionListener {

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
}
