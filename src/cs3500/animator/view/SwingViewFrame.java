package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import cs3500.animator.model.IAnimation;

/**
 * This class represents a visual representation of an animation using swing. --- TODO
 */
public class SwingViewFrame extends JFrame implements AnimationView {

  private IAnimation animation;
  private SwingViewPanel panel;
  private int tick;
  private int fps;
  private Timer timer;

  /**
   * Constructor --- TODO make better
   *
   * @param windowTitle the title of the main window
   * @param animation   the animation that this view will visualize
   * @param width       the width of the canvas
   * @param height      the height of the canvas
   * @param fps         the framerate of this animation in frames per second
   */
  public SwingViewFrame(String windowTitle, IAnimation animation, int width, int height, int fps) {
    super(windowTitle);

    setSize(width, height);
    setLocation(0, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.animation = animation;
    this.panel = new SwingViewPanel();
    this.panel.setFrame(animation.getFrame(0));
    this.add(panel);
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
    }
  }

  @Override
  public void renderAnimation() throws IOException {
    this.setVisible(true);
    timer = new Timer(1000 / fps, new MyActionListener());
    timer.start();
    while (tick < animation.getNFrames()) {
      // TODO



      this.repaint();
    }
    timer.stop();
  }
}
