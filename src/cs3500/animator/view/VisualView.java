package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs3500.animator.model.IAnimation;

/**
 * This class represents a visual representation of an animation using swing. --- TODO
 */
public class VisualView extends JFrame implements AnimationView {

  private Timer timer;
  private final IAnimation animation;
  private final SwingViewPanel panel;
  private int tick;
  private final int fps;

  /**
   * Constructor --- TODO make better
   *
   * @param windowTitle the title of the animation window
   * @param animation   the animation that this view will visualize
   * @param fps         the framerate of this animation in frames per second
   */
  public VisualView(String windowTitle, IAnimation animation, int fps) {
    super(windowTitle);

    setSize(animation.getWindowWidth(), animation.getWindowHeight());
    setLocation(0, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.animation = animation;
    this.panel = new SwingViewPanel();
    this.panel.setFrame(animation.getFrame(0));
    this.add(panel);
    this.timer = new Timer(1000 / fps, new MyActionListener());
    tick = 0;
    this.fps = fps;
  }

  /**
   * This ActionListener class increments the tick every 1000 / fps milliseconds.
   */
  private class MyActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
      tick++;
      panel.setFrame(animation.getFrame(tick));
      repaint();
      if (tick >= animation.getNFrames() - 1) {
        timer.stop();
      }
    }
  }

  @Override
  public void renderAnimation() {
    this.setVisible(true);
    tick = 0;
    timer.start();
  }
}
